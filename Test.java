import java.util.ArrayList;
import java.util.List;


public class Test {
	public static void main(String[] args){
		
		/**
		 * buildBySum example
		 */
		List<Object[]> testList = new ArrayList<Object[]>();
		
		testList.add(new Object[]{"CompanyA", "DepartmentA", "TeamA", 3});
		testList.add(new Object[]{"CompanyA", "DepartmentA", "TeamB", 2});
		testList.add(new Object[]{"CompanyA", "DepartmentB", "TeamB", 1});
		testList.add(new Object[]{"CompanyA", "DepartmentB", "TeamC", 7});
		testList.add(new Object[]{"CompanyB", "DepartmentB", "TeamD", 13});
		testList.add(new Object[]{"CompanyB", "DepartmentC", "TeamD", 17});
		testList.add(new Object[]{"CompanyB", "DepartmentC", "TeamD", 5});

		//Building HierarchicalStatistic Tree
		IHierarchicalStatisticTree<Object> HST = new HierarchicalStatisticTree<Object>();
		HST.buildTreeBySum(testList);
		
		HST.printTree();
		//	 Output:
		//		[TOTAL:48]
		//		[TOTAL:48][CompanyA:13]
		//		[TOTAL:48][CompanyA:13][DepartmentA:5]
		//		[TOTAL:48][CompanyA:13][DepartmentA:5][TeamA:3]
		//		[TOTAL:48][CompanyA:13][DepartmentA:5][TeamB:2]
		//		[TOTAL:48][CompanyA:13][DepartmentB:8]
		//		[TOTAL:48][CompanyA:13][DepartmentB:8][TeamB:1]
		//		[TOTAL:48][CompanyA:13][DepartmentB:8][TeamC:7]
		//		[TOTAL:48][CompanyB:35]
		//		[TOTAL:48][CompanyB:35][DepartmentB:13]
		//		[TOTAL:48][CompanyB:35][DepartmentB:13][TeamD:13]
		//		[TOTAL:48][CompanyB:35][DepartmentC:22]
		//		[TOTAL:48][CompanyB:35][DepartmentC:22][TeamD:22]
		 
		
		System.out.println(HST.getSum(new Object[]{"CompanyB", "DepartmentB"}));
		//Output:13
		System.out.println(HST.getSum(new Object[]{"CompanyA", "DepartmentB", "TeamC"}));
		//Output:7
		System.out.println(HST.getSum(new Object[]{}));
		//Output:48
		System.out.println(HST.getSum(new Object[]{"CompanyB", "DepartmentC", null}));		
		//Output:0 (No Such Key)
		System.out.println(HST.getSum(new Object[]{"CompanyB", null, "TeamD"}));		
	    //Output:0 (No Such Key)
		
	}
	
}
