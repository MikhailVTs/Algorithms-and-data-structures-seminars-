package task_3;

public class ReversLinkedList {

	private static Unit basic;

	static class Unit {

		int value;
		Unit next;

		Unit(int val) {
			value = val;
			next = null;
		}
	}

	Unit linkedListRevers(Unit unit) {
		Unit previous = null;
		Unit current = unit;
		Unit next = null;
		while (current != null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		unit = previous;
		return unit;
	}

	void printList(Unit node) {
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
	}

	public static void main(String[] args) {
		ReversLinkedList list = new ReversLinkedList();
		list.basic = new Unit(0);
		list.basic.next = new Unit(1);
		list.basic.next.next = new Unit(2);
		list.basic.next.next.next = new Unit(3);
		list.basic.next.next.next.next = new Unit(4);
		list.basic.next.next.next.next.next = new Unit(5);

		System.out.println();
		System.out.print("До разворота связного списка - ");
		list.printList(basic);

		System.out.print("\n----------------------------------------------");
		basic = list.linkedListRevers(basic);

		System.out.println();
		System.out.print("После разворота связного списка  - ");
		list.printList(basic);
		System.out.println();
	}
}
