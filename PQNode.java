

public class PQNode<T> {
public T data;
public int priority;
public PQNode<T> next;
public PQNode(T e,int p) {
	data=e;
	next=null;
	priority=p;
}
}
