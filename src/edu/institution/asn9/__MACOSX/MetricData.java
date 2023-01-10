package edu.institution.asn9.__MACOSX;

public class MetricData implements Comparable<MetricData>{

	// the sort algorithm used to sort the data
	private SortAlgorithm sortAlgorithm;
	
	// the time complexity for the sort algorithm
	private TimeComplexity timeComplexity;

	// the time (in milliseconds) that it took to sort the data
	private long executionTime;

	//Create a public constructor which accepts a SortAlgorithm parameter and set the supplied parameter to its equivalent property.
	public MetricData(SortAlgorithm sortAlgorithm) {
		
		this.sortAlgorithm = sortAlgorithm;
		
	}
	
	//	Generate the getter/setters for the three properties. Since the SortAlgorithm will be supplied on the constructor
	// in order to instantiate this class, you do not need the setter for this property.
	
	public SortAlgorithm getSortAlgorithm() {
		return sortAlgorithm;
	}

	public TimeComplexity getTimeComplexity() {
		return timeComplexity;
	}

	public void setTimeComplexity(TimeComplexity timeComplexity) {
		this.timeComplexity = timeComplexity;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sortAlgorithm == null) ? 0 : sortAlgorithm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MetricData other = (MetricData) obj;
		if (sortAlgorithm != other.sortAlgorithm)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MetricData [sortAlgorithm=" + sortAlgorithm + ", timeComplexity=" + timeComplexity + ", executionTime="
				+ executionTime + "]";
	}


	@Override
	public int compareTo(MetricData data) {
		return ((Long)this.getExecutionTime()).compareTo(data.getExecutionTime());
	}
	
}
