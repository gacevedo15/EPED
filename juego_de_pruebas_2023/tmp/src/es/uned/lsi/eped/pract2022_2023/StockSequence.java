package es.uned.lsi.eped.pract2022_2023;

import es.uned.lsi.eped.DataStructures.IteratorIF;
import es.uned.lsi.eped.DataStructures.List;
import es.uned.lsi.eped.DataStructures.SequenceIF;

public class StockSequence implements StockIF {

	protected SequenceIF<StockPair> stock;
	
	/* Constructor de la clase */
	public StockSequence() {
		this.stock = new List<StockPair>();
	}

	/* Devuelve el valor indexado bajo el indice p.
	 * @PRE: p != ""
	 * Si no existe un valor indexado bajo el indice p,
	 * devuelve el valor -1.
	 */
	public int retrieveStock(String p) {
		IteratorIF<StockPair> iterator = stock.iterator();
		while(iterator.hasNext()) {
			StockPair pair = iterator.getNext();
			if(pair.getProducto().equals(p)) {
				return pair.getUnidades();
			}
		}
		return -1;
	}

	/* Indexa el valor u bajo el indice p.
	 * @PRE: p != "" y u >= 0
	 * Si ya habia un valor bajo el mismo indice,
	 * el nuevo valor substituye al anterior.
	 */
	public void updateStock(String p, int u) {
		IteratorIF<StockPair> iterator = stock.iterator();
		while(iterator.hasNext()) {
			StockPair pair = iterator.getNext();
			if(pair.getProducto().equals(p)) {
				pair.setUnidades(u);
				return;
			}
		}
		StockPair newPair = new StockPair(p, u);
		((List<StockPair>) this.stock).insert(getIndex(newPair), newPair);
	}

	/* Devuelve una secuencia de todos los pares <p,u>
	 * presentes en el stock tales que:
	 * -El valor indexado bajo el índice p es u
	 * -El índice p comienza por la cadena prefix
	 * Además, la secuencia deberá estar ordenada según
	 * el orden alfabético de los productos
	 */
	public SequenceIF<StockPair> listStock(String prefix) {
		List<StockPair> stockPairs = new List<StockPair>();
		IteratorIF<StockPair> iterator = stock.iterator();
		while(iterator.hasNext()) {
			StockPair pair = iterator.getNext();
			if (pair.getProducto().startsWith(prefix)) {
				stockPairs.insert(stockPairs.size() + 1, pair);
			}
		}
		return stockPairs;
	}

	/*
	 * Devuelve la posición en la que se debe insertar el par para que la lista
	 * quede ordenada alfabéticamente.
	 */
	private int getIndex(StockPair pair) {
		int index = 1;
		while (index <= this.stock.size()) {
			StockPair currentPair = ((List<StockPair>) this.stock).get(index); // cast a List<StockPair>
			if (currentPair.getProducto().compareTo(pair.getProducto()) > 0) {
				return index;
			}
			index++;
		}
		return index;
	}

}
