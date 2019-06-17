package pe.jaav.sistemas.utiles;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import pe.jaav.common.util.UtilesCommons;


public  class FunctionalInterfaceUtiles {

	/**FUNCTIONAL INTERFACES  GENERALES */
	public static Comparator<? super Date> comparatorDate = (Date p1, Date p2) -> p1.compareTo(p2);
	public static Comparator<? super Integer> comparatorInt = (Integer p1, Integer p2) -> p1.compareTo(p2);
	public static Comparator<? super Double> comparatorDouble = (Double p1, Double p2) -> p1.compareTo(p2);
	public static Comparator<? super Float> comparatorFloat = (Float p1, Float p2) -> p1.compareTo(p2);
	public static Comparator<? super BigDecimal> comparatorBigDec = (BigDecimal p1, BigDecimal p2) -> p1.compareTo(p2);
	
	
	/**FUNCTIONAL METHODS PERSONALIZADAS*/
	public static List<Integer>  elminarRepeticionesListInt(List<Integer> listaOriginal){
		List<Integer> listaSinDuplicados = null;
		if(UtilesCommons.noEsVacio(listaOriginal)){
			Set<Integer> allItems = new HashSet<>();
			listaSinDuplicados = listaOriginal.stream()
			        .filter(n -> !allItems.add(n)) //Set.add() returns false if the item was already in the set.
			        .collect(Collectors.toList());						
		}
		return listaSinDuplicados;
	}
	
	
	
	/**FUNCTIONAL INTERFACES  PERSONALIZADAS*/
	
	@FunctionalInterface
	public interface TriFunction<T,U,S, R> {
	 
	/**
	* Applies this function to the given arguments.
	*
	* @param t the first function argument
	* @param u the second function argument
	* @param s the third function argument
	* @return the function result
	*/
	R apply(T t, U u, S s);
	}
}
