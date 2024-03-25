import java.util.LinkedList;

public class Process 
	{
		public LinkedList<Integer> allocation = new LinkedList<Integer>();
		
		public LinkedList<Integer> max = new LinkedList<Integer>();
		
		public LinkedList<Integer> need = new LinkedList<Integer>();
		
		private Boolean finish;
		
		private Boolean isValid;

		public Boolean getFinish() 
			{
				return finish;
			}

		public void setFinish(Boolean finish) 
			{
				this.finish = finish;
			}

		public Boolean getIsValid() 
			{
				return isValid;
			}

		public void setIsValid(Boolean isValid) 
			{
				this.isValid = isValid;
			}
		
	}//end of Process class
