package entity;

import java.util.List;
/**
 * �ѷ����ʼ��ķ�ҳ
 * @author zei
 *
 */
public class SPage {
	private int pageIndex;   //��ǰҳ��
	private int pageIndexA;  //��ҳ��
	private int pageCount;   //ÿҳ��ʾ�ʼ�����
	private int pageCountA;  //�ʼ�������
	private List<Send> list;  //ÿҳ��ʾ���ʼ�
	
	public SPage() {
		super();
	}
	public SPage(int pageIndex, int pageIndexA, int pageCount, int pageCountA,
			List<Send> list) {
		super();
		this.pageIndex = pageIndex;
		this.pageIndexA = pageIndexA;
		this.pageCount = pageCount;
		this.pageCountA = pageCountA;
		this.list = list;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageIndexA() {
		return pageIndexA;
	}
	public void setPageIndexA(int pageIndexA) {
		this.pageIndexA = pageIndexA;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageCountA() {
		return pageCountA;
	}
	public void setPageCountA(int pageCountA) {
		this.pageCountA = pageCountA;
		if(pageCountA==0){
			this.pageIndexA=1;
		}else{
			this.pageIndexA = (pageCountA%pageCount)==0?(pageCountA/pageCount):(pageCountA/pageCount+1);
		}
	}
	public List<Send> getList() {
		return list;
	}
	public void setList(List<Send> list) {
		this.list = list;
	}
}
