package es.uned.lsi.eped.pract2022_2023;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.SequenceIF;

public class StockSequence implements StockIF {

	protected SequenceIF<StockPair> stock;

	/* Constructor de la clase */
	public StockSequence() {
		this.stock = new List<>();
	}

	/* Devuelve el valor indexado bajo el indice p.
	 * @PRE: p != ""
	 * Si no existe un valor indexado bajo el indice p,
	 * devuelve el valor -1.
	 */
	public int retrieveStock(String p) {
		int low = 1;  // Inicio del rango (posición 1)
		int high = stock.size();

		while (low <= high) {
			int mid = low + (high - low) / 2;
			StockPair pair = ((List<StockPair>) stock).get(mid);

			int comparison = pair.getProducto().compareTo(p);
			if (comparison == 0) {
				return pair.getUnidades();
			} else if (comparison < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1;
	}

	/* Indexa el valor u bajo el indice p.
	 * @PRE: p != "" y u >= 0
	 * Si ya había un valor bajo el mismo índice,
	 * el nuevo valor substituye al anterior.
	 */
	public void updateStock(String p, int u) {
		if (p == null || p.isEmpty() || u < 0) {
			return;
		}

		StockPair newPair = new StockPair(p, u);
		int index = getIndex(newPair);

		if (index <= stock.size() && ((List<StockPair>) stock).get(index) != null && ((List<StockPair>) stock).get(index).getProducto().equals(p)) {
			((List<StockPair>) stock).get(index).setUnidades(u);
		} else {
			((List<StockPair>) stock).insert(index, newPair);
		}
	}

	/*
	 * Devuelve el índice en el que se encuentra el par utilizando búsqueda binaria
	 */
	private int getIndex(StockPair pair) {
		int low = 1;
		int high = stock.size();

		while (low <= high) {
			int mid = (low + high) / 2;
			StockPair currentPair = ((List<StockPair>) stock).get(mid);

			int comparison = currentPair.getProducto().compareTo(pair.getProducto());
			if (comparison == 0) {
				return mid;
			} else if (comparison < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}

	/* Devuelve una secuencia de todos los pares <p,u>
	 * presentes en el stock tales que:
	 * -El valor indexado bajo el índice p es u
	 * -El índice p comienza por la cadena prefix
	 * Además, la secuencia deberá estar ordenada según
	 * el orden alfabético de los productos
	 */
	public SequenceIF<StockPair> listStock(String prefix) {
		List<StockPair> stockPairs = new List<>();
		IteratorIF<StockPair> iterator = stock.iterator();

		while (iterator.hasNext()) {
			StockPair pair = iterator.getNext();

			if (pair.getProducto().startsWith(prefix)) {
				stockPairs.insert(stockPairs.size() + 1, pair);
			} else if (pair.getProducto().compareTo(prefix) > 0) {
				break; // El elemento es mayor que el prefijo, no hay más elementos con ese prefijo
			}
		}

		return stockPairs;
	}


}

