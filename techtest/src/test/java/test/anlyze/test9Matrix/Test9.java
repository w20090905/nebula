package test.anlyze.test9Matrix;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Test9 {
	public static void main(String[] args) {
		String[] sdata = new String[] { 
				"   7  8  ",
				"    4  3 ",
				"     9  1",
				
				"6  5     ",
				" 1  3  4 ",
				"  5  1  7",
				
				"5  2  6  ",
				" 3  8  9 ",
				"  7     2"};

		int[] data = new int[81];

		for (int row = 0; row < 9; row++) {
			char[] rowData = sdata[row].toCharArray();
			for (int j = 0; j < 9; j++) {
				if(rowData[j]!=' '){
					data[row * 9 + j] = rowData[j] - '0' ;					
				}else{
					data[row * 9 + j] = 0;					
				}
			}
		}
		
		tryResolve(data);

	}

	static class Item {
		public Item(int row, int column, int value) {
			this.row = row;
			this.column = column;
			this.value = value;
		}

		int row;
		int column;
		int value;

		ItemStatus status;

		@Override
		public String toString() {
			return "[" + row + "," + column + "] -> " + value + "(" + status + ")";
		}

		public boolean canbe() {
			return status == ItemStatus.ForceOK || status == ItemStatus.OK;
		}

	}

	static enum ItemStatus {
		ForceOK, ForceTryFailure, OK, MaybeOK, MaybeNot
	}

	static int[] tryResolve(int[] data) {

		long startNano = System.nanoTime();

		LinkedList<Item> e;

		boolean succeed = true;

		@SuppressWarnings("unchecked")
		LinkedList<Item>[] buf = new LinkedList[81];

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				buf[row * 9 + column] = new LinkedList<Item>();
				buf[row * 9 + column].add(new Item(row, column, data[row * 9 + column]));
			}
		}

		printGraph("Init status", buf);

		OuterFor: for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				e = buf[row * 9 + column];

				if (e.getFirst().value != 0)
					continue;

				Item i = e.pop();
				e.clear();
				e.push(i);

				for (int d = 1; d <= 9; d++) {
					if (maybe(buf, d, row, column)) {
						Item it = new Item(row, column, d);
						it.status = ItemStatus.MaybeOK;
						e.add(it);
					}
				}

				if (e.size() == 2) {
					e.pop();
					System.out.println("exclude(" + row + "," + column + ") : " + e.getFirst().value);
					e.getFirst().status = ItemStatus.OK;
					row = -1;
					column = 0;

					continue OuterFor;
				}
			}
		}

		printGraph("before last try", buf);
		Stack<Item> sa = new Stack<Test9.Item>();
		OuterFor: for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				e = buf[row * 9 + column];
				Iterator<Item> ii = e.listIterator(1);
				int cnt = 0;
				while (ii.hasNext()) {
					Item i = ii.next();
					if (i.status == ItemStatus.ForceOK)
						break;
					if (i.status == ItemStatus.OK)
						break;
					if (i.status == ItemStatus.MaybeOK) {
						cnt++;
					}
				}

				if (cnt != 2)
					continue;

				ii = e.listIterator(1);
				while (ii.hasNext()) {
					Item i = ii.next();
					if (i.status == ItemStatus.MaybeOK) {
						sa.push(i);
					}
				}

				break OuterFor;
			}
		}

		if (!sa.isEmpty()) {
			sa.peek().status = ItemStatus.ForceOK;
		}

		MainFor: for (int c = 0; c < 1000 && !sa.isEmpty(); c++) {

			printGraph("Step[" + (c + 1) + "]  Start parce when set " + sa.peek().toString(), buf);

			for (int row = 0; row < 9; row++) {
				for (int column = 0; column < 9; column++) {
					e = buf[row * 9 + column];
					Iterator<Item> ii = e.listIterator(1);
					while (ii.hasNext()) {
						Item item = ii.next();
						if (item.status == ItemStatus.ForceOK || item.status == ItemStatus.ForceTryFailure) {
						} else {
							item.status = ItemStatus.MaybeNot;
						}
					}
				}
			}

			succeed = true;

			OuterFor: for (int row = 0; row < 9; row++) {
				InnerFor: for (int column = 0; column < 9; column++) {
					e = buf[row * 9 + column];

					if (e.getFirst().value != 0)
						continue;

					Iterator<Item> ii = e.listIterator(1);

					while (ii.hasNext()) {
						Item item = ii.next();
						if (item.status == ItemStatus.ForceOK) {
							continue InnerFor;
						} else if (item.status == ItemStatus.OK) {
							continue InnerFor;
						}
					}

					int cnt = 0;
					Item lastOk = null;

					ii = e.listIterator(1);
					while (ii.hasNext()) {
						Item item = ii.next();

						if (maybe(buf, item.value, row, column)) {
							item.status = ItemStatus.MaybeOK;
							lastOk = item;
							cnt++;
						} else {
							item.status = ItemStatus.MaybeNot;
						}
					}

					if (cnt == 1) {
						lastOk.status = ItemStatus.OK;
						row = -1;
						column = 0;
						succeed = true;
						continue OuterFor;
					} else {
						succeed = false;
					}

					if (cnt == 0) {
						System.out.println("Fail At " + e.getFirst());
						printStack(sa);
						while (!sa.isEmpty()) {
							if (sa.peek().status == ItemStatus.ForceOK) {
								Item item = sa.pop();
								item.status = ItemStatus.MaybeOK;
							} else {
								sa.peek().status = ItemStatus.ForceOK;

								printStack(sa);

								row = -1;
								column = 0;
								continue MainFor;
							}
						}

						printGraph("Exception", buf);
						break MainFor;
					}
				}
			}

			if (succeed) {
				break MainFor;
			}

			printGraph("Finish parce when set : " + sa.peek().toString(), buf);

			OuterFor: for (int tryDeep = 2; tryDeep < 9; tryDeep++) {
				for (int row = 0; row < 9; row++) {
					for (int column = 0; column < 9; column++) {
						e = buf[row * 9 + column];
						Iterator<Item> ii = e.listIterator(1);
						int cnt = 0;
						while (ii.hasNext()) {
							Item i = ii.next();
							if (i.status == ItemStatus.ForceOK) {
								cnt = 0;
								break;
							} else if (i.status == ItemStatus.OK) {
								cnt = 0;
								break;
							} else if (i.status == ItemStatus.MaybeOK) {
								cnt++;
							}
						}

						if (cnt != tryDeep)
							continue;

						ii = e.listIterator(1);
						while (ii.hasNext()) {
							Item i = ii.next();
							if (i.status == ItemStatus.MaybeOK) {
								sa.push(i);
							}
						}

						sa.peek().status = ItemStatus.ForceOK;

						System.out.println("Add path");
						printStack(sa);

						break OuterFor;
					}
				}
			}
		}

		long endNano = System.nanoTime();
		System.out.println("");
		System.out.print("\n----  Cost " + (endNano - startNano) / 1000000 + " ms");
		if (succeed) {
			System.out.println("**************************************");
			System.out.println("*                                    *");
			System.out.println("*             SUCCEED                *");
			System.out.println("*                                    *");
			System.out.println("**************************************");
			printGraph("", buf);
		} else {
			System.out.println("**************************************");
			System.out.println("*                                    *");
			System.out.println("*             FAILURE                *");
			System.out.println("*                                    *");
			System.out.println("**************************************");
		}

		return new int[0];
	}

	static void printStack(Stack<Item> sa) {
		System.out.println("=========Stack<Item> sa========");
		for (Item i : sa) {
			System.out.println("  >>   " + i.toString() + "'" + i.status);
		}
	}

	static boolean maybe(LinkedList<Item>[] buf, int d, int checkedRow, int checkedColumn) {

		LinkedList<Item> e;

		for (int row = 0; row < 9; row++) {
			if (checkedRow == row)
				continue;

			e = buf[row * 9 + checkedColumn];

			if (e.getFirst().value == d) {
				return false;
			}

			for (Item item : e) {
				if (item.value == d && item.canbe()) {
					return false;
				}
			}

		}
		for (int column = 0; column < 9; column++) {
			if (checkedColumn == column)
				continue;

			e = buf[checkedRow * 9 + column];

			if (e.getFirst().value == d) {
				return false;
			}

			for (Item item : e) {
				if (item.value == d && item.canbe()) {
					return false;
				}
			}
		}

		int subGridStartRow = checkedRow - checkedRow % 3;
		int subGridStartColumn = checkedColumn - checkedColumn % 3;

		for (int row = subGridStartRow; row < subGridStartRow + 3; row++) {
			for (int column = subGridStartColumn; column < subGridStartColumn + 3; column++) {
				e = buf[row * 9 + column];

				if (checkedColumn == column)
					continue;

				if (e.getFirst().value == d) {
					return false;
				}

				for (Item item : e) {
					if (item.value == d && item.canbe()) {
						return false;
					}
				}
			}
		}

		return true;
	}

	static void printGraph(String msg, LinkedList<Item>[] buf) {
		System.out.print("\n================================================\n" + msg);

		for (int row = 0; row < 9; row++) {
			System.out.println("\n");
			InnerFor: for (int column = 0; column < 9; column++) {
				System.out.print("\t");
				LinkedList<Item> e = buf[row * 9 + column];
				if (e.size() == 1)
					System.out.print("[" + e.getFirst().value + "]");
				else {
					for (Item t : e) {
						if (t.status == ItemStatus.ForceOK ) {
							System.out.print("<" + t.value + ">");
							continue InnerFor;
						}
					}
					for (Item t : e) {
						if (t.status == ItemStatus.OK) {
							System.out.print("(" + t.value + ")");
							continue InnerFor;
						}
					}
					for (Item t : e) {
						if (t.status == ItemStatus.MaybeOK)
							System.out.print("" + t.value + "");
					}
					for (Item t : e) {
						if (t.status == ItemStatus.MaybeNot)
							System.out.print("-" + t.value + "");
					}
				}
			}
		}
	}
}
