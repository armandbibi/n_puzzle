package webEngineering.webEngineering.project.taquin;

public enum ExpectedSolutionCalculator {

	CLASSIC("classic")
	{
		@Override
		public int[][] getSolution(int dimension){
			int[][] solution = new int[dimension][dimension];
			
			int x = 0;
			int y = 0;
			int i = 0;
			while (dimension >= 0 ) {
				while (y < dimension - 1 && solution[x][y + 1] == 0)
					solution[x][y++] = ++i;
				while (x < dimension - 1 && solution[x + 1][y] == 0)
					solution[x++][y] = ++i;
				while (y > 0 && solution[x][y - 1] == 0)
					solution[x][y--] = ++i;
				while (x > 0 && solution[x - 1][y] == 0)
					solution[x--][y] = ++i;
				dimension--;

			}
			return solution;
		};
	},
	SORTEDTABLE("sortedTable")
	{
		@Override
		public int[][] getSolution(int dimension){

			int[][] solution = new int[dimension][dimension];
			
			for (int i = 0; i < dimension; i++)
				for (int j = 0; j < dimension; j++)
					solution[i][j] = i * dimension + j + 1;
			solution[dimension - 1][dimension - 1] = 0;
			return solution;
		}
	},
	EASYSHUFFLED_SORTEDTABLE("easy shuffled  for sorted table model")
	{
		@Override
		public int[][] getSolution(int dimension){
			int[][] startingPuzzle = {
					{
						5,1,8,3
					},
					{
						6,7,2,4
					},
					{
						0,9,10,12
					},
					{
						13,14,11,15
					}
			};
			
			return startingPuzzle;
		}
	}
	;
	ExpectedSolutionCalculator(String name) {
		
	}
	
	public int[][] getSolution(int dimension) {
		return null;
	}
}
