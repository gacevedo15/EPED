package es.uned.lsi.eped.pract2022_2023;

//import ...

import es.uned.lsi.eped.DataStructures.GTreeIF;
import es.uned.lsi.eped.DataStructures.SequenceIF;

public class StockTree implements StockIF {

	protected GTreeIF<Node> stock;
	
	/* Constructor de la clase */

	public StockTree() {
		//TODO
	}

//TODO

	/* Devuelve el valor indexado bajo el indice p.
	 * @PRE: p != ""
	 * Si no existe un valor indexado bajo el indice p,
	 * devuelve el valor -1.
	 */
	public int retrieveStock(String p) {
		//TODO
		return -1;
	}

	/* Indexa el valor u bajo el indice p.
	 * @PRE: p != "" y u >= 0
	 * Si ya habia un valor bajo el mismo indice,
	 * el nuevo valor substituye al anterior.
	 */
	public void updateStock(String p, int u) {
		//TODO
	}

	/* Devuelve una secuencia de todos los pares <p,u>
	 * presentes en el stock tales que:
	 * -El valor indexado bajo el índice p es u
	 * -El índice p comienza por la cadena prefix
	 * Además, la secuencia deberá estar ordenada según
	 * el orden alfabético de los productos
	 */
	public SequenceIF<StockPair> listStock(String prefix) {
		//TODO
		return null;
	}

}
