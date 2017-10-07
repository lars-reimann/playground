public final class SelectionSort extends AbstractSorter {

	public void sort() {
		for (int i = 0; i < array.length - 1 && !intArrayGUI.isStopped(); i++) {
			int indexMin = i;
	        for (int j = i + 1; j < array.length; j++) {
	            if (array[j] < array[indexMin]) {
	                indexMin = j;
	            }
	        }
            swap(i, indexMin);
        }
	}
	
    public void run() {
    	sort();
    }
}
