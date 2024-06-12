package Data_Access_Object;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import models.BacSi;

public interface Interface<T> {
	
	public int Add(T t);
	
	public int Update (T t);
	
	public int Delete (T t);
	
	public ObservableList<T> selectAll();
	
	public T seclectById(T t);
	
	public ArrayList<T> selectByCondition(String condition);
	
}
