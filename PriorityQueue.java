

public class PriorityQueue <T>{
	private PQNode<T>head;

	private int size;
	public PriorityQueue() {
		head=null; size=0;
	}
	public void Enqueue(T e ,int p) {
		PQNode<T> n=new PQNode<T>(e,p);
		if(head==null||head.priority<p) {
			n.next=head;
			head=n;
		}
		else {
			
			
		PQNode<T> pp=head;
		PQNode<T> q=null;
		while(pp!=null&&p<=pp.priority) {
			q=pp;
			pp=pp.next;
		}
		n.next=pp;
		q.next=n;
			
			
		
	}
		size++;
	}
	public T serve() {
		PQNode<T> pq=head;
		
		head=head.next;
		size--;
		return pq.data;
	}
	public boolean isEmpty() {
		
		return head==null;
	}
	
}
