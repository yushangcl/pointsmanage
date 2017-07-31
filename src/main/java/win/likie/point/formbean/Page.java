package win.likie.point.formbean;

import java.util.List;

public class Page<T> {
	
	private int pageSize ;//每页数量
	private int indexPage ;//当前页码
	private int totalPages;//总页数
	private Long totalCount;//记录总数
	private List<T> content;
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	/**
	 * 是否首页
	 */
	public boolean isFirstPage(){
		if(indexPage== 0)
			return true;
		else
			return false;
	}
	/**
	 * 是否最后一页
	 */
	public boolean isLastPage(){
		if(indexPage== totalPages)
			return true;
		else
			return false;
	}
	/**
	 * 是否有下一页
	 */
	public boolean hasNextPage(){
		if(indexPage< totalPages)
			return true;
		else
			return false;
	}
	/**
	 * 是否有上一页
	 */
	public boolean hasPreviousPage(){
		if(indexPage>0)
			return true;
		else
			return false;
	}
}
