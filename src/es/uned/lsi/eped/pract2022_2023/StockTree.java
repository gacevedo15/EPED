package es.uned.lsi.eped.pract2022_2023;

import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.DataStructures.GTree;
import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.SequenceIF;
import es.uned.lsi.eped.DataStructures.List;

public class StockTree implements StockIF {

	protected GTreeIF<Node> stock;
	
	/* Constructor de la clase */

	public StockTree() {
		this.stock = new GTree<Node>();
	}


	/* Devuelve el valor indexado bajo el indice p.
	 * @PRE: p != ""
	 * Si no existe un valor indexado bajo el indice p,
	 * devuelve el valor -1.
	 */
	public int retrieveStock(String p) {
		if (p == null || p.isEmpty()) {
			return -1;
		}

		GTreeIF<Node> current = stock;
		for (int i = 0; i < p.length(); i++) {
			char letter = p.charAt(i);
			boolean found = false;
			IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
			while (it.hasNext()) {
				GTreeIF<Node> child = it.getNext();
				if (child.getRoot() instanceof NodeInner && ((NodeInner) child.getRoot()).getLetter() == letter) {
					current = child;
					found = true;
					break;
				}
			}
			if (!found) {
				return -1;
			}
		}

		IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
		while (it.hasNext()) {
			GTreeIF<Node> child = it.getNext();
			if (child.getRoot() instanceof NodeInfo) {
				return ((NodeInfo) child.getRoot()).getUnidades();
			}
		}

		return -1;
	}



	/* Indexa el valor u bajo el indice p.
	 * @PRE: p != "" y u >= 0
	 * Si ya habia un valor bajo el mismo indice,
	 * el nuevo valor substituye al anterior.
	 * 
	 */
	public void updateStock(String p, int u) {
		if (p == null || p.isEmpty() || u < 0) {
			return;
		}

		GTreeIF<Node> current = stock;
		for (int i = 0; i < p.length(); i++) {
			char letter = p.charAt(i);
			boolean found = false;
			IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
			while (it.hasNext()) {
				GTreeIF<Node> child = it.getNext();
				if (child.getRoot() instanceof NodeInner && ((NodeInner) child.getRoot()).getLetter() == letter) {
					current = child;
					found = true;
					break;
				}
			}
			if (!found) {
				GTreeIF<Node> hijo = new GTree<>();
				hijo.setRoot(new NodeInner(letter));
				int index = getIndex(current, letter);
				current.addChild(index, hijo);
				current = hijo;
			}
		}

		boolean updated = false;
		IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
		while (it.hasNext()) {
			GTreeIF<Node> child = it.getNext();
			if (child.isLeaf() && child.getRoot() instanceof NodeInfo) {
				((NodeInfo) child.getRoot()).setUnidades(u);
				updated = true;
				break;
			}
		}
		if (!updated) {
			GTreeIF<Node> hijo = new GTree<>();
			hijo.setRoot(new NodeInfo(u));
			int index = getIndex(current, (char) 0);
			current.addChild(index, hijo);
		}
	}

	private int getIndex(GTreeIF<Node> current, char letter) {
		IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
		int index = 1;
		while (it.hasNext()) {
			GTreeIF<Node> child = it.getNext();
			if (child.getRoot() instanceof NodeInner) {
				char childLetter = ((NodeInner) child.getRoot()).getLetter();
				if (childLetter < letter) {
					index++;
				} else {
					break;
				}
			} else if (child.getRoot() instanceof NodeInfo) {
				index++;
			}
		}
		return index;
	}


	/* Devuelve una secuencia de todos los pares <p,u>
	 * presentes en el stock tales que:
	 * -El valor indexado bajo el índice p es u
	 * -El índice p comienza por la cadena prefix
	 * Además, la secuencia deberá estar ordenada según
	 * el orden alfabético de los productos
	 */
	public SequenceIF<StockPair> listStock(String prefix) {
		List<StockPair> result = new List<>();
		GTreeIF<Node> current = stock;
		for (int i = 0; i < prefix.length(); i++) {
			char letter = prefix.charAt(i);
			boolean found = false;
			IteratorIF<GTreeIF<Node>> it = current.getChildren().iterator();
			while (it.hasNext()) {
				GTreeIF<Node> child = it.getNext();
				if (child.getRoot() instanceof NodeInner && ((NodeInner) child.getRoot()).getLetter() == letter) {
					current = child;
					found = true;
					break;
				}
			}
			if (!found) {
				return result;
			}
		}

		listStockRecursive(current, prefix, result);

		return result;
	}

	/*
	 * Recorre el arbol en profundidad y va añadiendo los pares <p,u> a la lista result de manera recursiva
	 */
	private void listStockRecursive(GTreeIF<Node> node, String prefix, List<StockPair> result) {
		IteratorIF<GTreeIF<Node>> it = node.getChildren().iterator();
		while (it.hasNext()) {
			GTreeIF<Node> child = it.getNext();
			if (child.getRoot() instanceof NodeInfo) {
				result.insert(result.size() + 1,new StockPair(prefix, ((NodeInfo) child.getRoot()).getUnidades()));
			} else if (child.getRoot() instanceof NodeInner) {
				listStockRecursive(child, prefix + ((NodeInner) child.getRoot()).getLetter(), result);
			}
		}
	}


}
