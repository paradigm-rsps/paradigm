import java.util.Iterator;

public class IterableNodeDequeDescendingIterator implements Iterator {
	IterableNodeDeque deque;
	Node field4097;
	Node last;

	IterableNodeDequeDescendingIterator(IterableNodeDeque var1) {
		this.last = null;
		this.setDeque(var1);
	}

	void setDeque(IterableNodeDeque var1) {
		this.deque = var1;
		this.start();
	}

	void start() {
		this.field4097 = this.deque != null ? this.deque.sentinel.previous : null;
		this.last = null;
	}

	public Object next() {
		Node var1 = this.field4097;
		if (var1 == this.deque.sentinel) {
			var1 = null;
			this.field4097 = null;
		} else {
			this.field4097 = var1.previous;
		}

		this.last = var1;
		return var1;
	}

	public void remove() {
		this.last.remove();
		this.last = null;
	}

	public boolean hasNext() {
		return this.deque.sentinel != this.field4097 && this.field4097 != null;
	}
}
