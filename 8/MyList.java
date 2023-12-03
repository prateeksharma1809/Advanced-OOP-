package h8;

import java.util.Comparator;

public class MyList<T> {
	
	private Object[] dataList= new Object[1];
	private int eleCounter = 0;

	public Object[] getDataList() {
		return dataList;
	}

	public int getEleCounter() {
		return eleCounter;
	}


	public void add(T data) {
		if(eleCounter<=dataList.length-1) {
			dataList[eleCounter]=data;
			eleCounter++;
		}else {
			createNewListAndCopyDataWithNewData(data);
		}
		
	}

	private void createNewListAndCopyDataWithNewData(T data) {
		Object[] tempList = new Object[dataList.length*2];
		for (int index = 0; index < eleCounter; index++) {
			tempList[index]=dataList[index];
		}
		tempList[eleCounter]=data;
		eleCounter++;
		dataList=tempList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ");
		for (int index = 0; index < eleCounter; index++) {
			builder.append(dataList[index].toString()
					+" ");
		}
		builder.append("/");
		builder.append(eleCounter);
		return builder.toString();
	}

	public void sort() {
		for (int index = 0; index < eleCounter-1; index++) {
			for (int index2 = 0; index2 < eleCounter-index-1; index2++) {
				Object ele1 = dataList[index2];
				Object ele2 = dataList[index2+1];
				if(ele1.hashCode()>ele2.hashCode()) {
					dataList[index2]=ele2;
					dataList[index2+1]=ele1;
				}
			}
			
		}
	}

	
	@SuppressWarnings("unchecked")
	public void sort(Comparator<T> myComparator) {
		for (int index = 0; index < eleCounter-1; index++) {
			for (int index2 = 0; index2 < eleCounter-index-1; index2++) {
				/*
				 * Type safety: Unchecked cast from Object to T
				 */
				T ele1 = (T) dataList[index2];
				T ele2 = (T) dataList[index2+1];
				if(0<myComparator.compare(ele1, ele2)) {
					dataList[index2]=ele2;
					dataList[index2+1]=ele1;
				}
			}
		}
	}

}
