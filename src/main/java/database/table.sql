create database 积分管理
go
CREATE TABLE [dbo].[客户信息](
	[客户编号] [int] IDENTITY(1,1) NOT NULL primary key,
	[客户电话] [char](11) NOT NULL unique,
	[客户姓名] [nchar](4) NULL,
	[已购积分] [int] NOT NULL default 0,
	[已换积分] [int] NOT NULL default 0,
	[剩余积分]  AS ([已购积分]-[已换积分]),
)
go

CREATE TABLE [dbo].[消费记录](
	[记录号] [int] IDENTITY(1,1) NOT NULL primary key,
	[客户电话] [char](11) NOT NULL foreign key  references 客户信息(客户电话),
	[消费日期] [smalldatetime] NULL default getdate(),
	[消费金额] [money] NULL,
	[备注信息] [nchar](50) NULL,
)
GO

CREATE TABLE [dbo].[兑换记录](
	[兑换记录号] [int] IDENTITY(1,1) NOT NULL primary key,
	[客户电话] [char](11) NOT NULL foreign key references 客户信息(客户电话) ,
	[兑换日期] [smalldatetime] NULL default getdate(),
	[兑换积分] [money] NULL,
	[备注] [varchar](50) NULL
)

GO

create trigger [dbo].[jf_ins] on [dbo].[兑换记录] for insert
as
declare @dhjf integer,@syjf integer
select @dhjf=兑换积分 from inserted
select @syjf=剩余积分 from 客户信息
 where 客户电话=(select 客户电话 from inserted )
if (@dhjf > @syjf)
   rollback transaction
else
update 客户信息 set 已换积分=已换积分+@dhjf
where 客户电话=(select 客户电话 from inserted )

GO

create trigger [dbo].[xf_ins] on [dbo].[消费记录] for insert
as
declare @xfje integer
select @xfje =消费金额 from inserted
update 客户信息 set 已购积分=已购积分 + @xfje
where 客户电话 =(select 客户电话 from inserted )
GO
