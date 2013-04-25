import java.util.ArrayList;
import java.util.List;


public class CountTreeDemo {
	public static void main(String[] args){
		
		/*
		 * Testing data
		 */
		List<Object[]> testList = new ArrayList<Object[]>();
		testList.add(new Object[]{"CompanyA", "DepartmentA", "TeamA"});
		testList.add(new Object[]{"CompanyA", "DepartmentA", "TeamB"});
		testList.add(new Object[]{"CompanyA", "DepartmentB", "TeamB"});
		testList.add(new Object[]{"CompanyA", "DepartmentB", "TeamC"});
		testList.add(new Object[]{"CompanyB", "DepartmentB", "TeamD"});
		testList.add(new Object[]{"CompanyB", "DepartmentC", "TeamD"});
		testList.add(new Object[]{"CompanyB", "DepartmentC", "TeamD"});

		/*
		 * Building HierarchicalStatistic Tree
		 */
		IHierarchicalStatisticTree<Object> HST = new CountTree<Object>();
		HST.buildTree(testList);
		
		/*
		 * Print out whole tree
		 */
		HST.printTree();
		//	 Output:
		//		[TOTAL:7]
		//		[TOTAL:7][CompanyA:4]
		//		[TOTAL:7][CompanyA:4][DepartmentA:2]
		//		[TOTAL:7][CompanyA:4][DepartmentA:2][TeamA:1]
		//		[TOTAL:7][CompanyA:4][DepartmentA:2][TeamB:1]
		//		[TOTAL:7][CompanyA:4][DepartmentB:2]
		//		[TOTAL:7][CompanyA:4][DepartmentB:2][TeamB:1]
		//		[TOTAL:7][CompanyA:4][DepartmentB:2][TeamC:1]
		//		[TOTAL:7][CompanyB:3]
		//		[TOTAL:7][CompanyB:3][DepartmentB:1]
		//		[TOTAL:7][CompanyB:3][DepartmentB:1][TeamD:1]
		//		[TOTAL:7][CompanyB:3][DepartmentC:2]
		//		[TOTAL:7][CompanyB:3][DepartmentC:2][TeamD:2]
		
		System.out.println(HST.getResult(new Object[]{"CompanyB", "DepartmentB"}));
		//Output:1
		System.out.println(HST.getResult(new Object[]{"CompanyA", "DepartmentB", "TeamC"}));
		//Output:1
		System.out.println(HST.getResult(new Object[]{}));
		//Output:7
		System.out.println(HST.getResult(new Object[]{"CompanyB", "DepartmentC", null}));		
		//Output:0 (No Such Key)
		System.out.println(HST.getResult(new Object[]{"CompanyB", null, "TeamD"}));		
		//Output:0 (No Such Key)
		
	}
	
}
