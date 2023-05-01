// TC: O(salaries.length)
// SC: O(1)

class Solution {
    public double average(int[] salaries) {
        int minSalary = Integer.MAX_VALUE;
        int maxSalary = Integer.MIN_VALUE;
        double sumOfSalaries = 0;
        
        for(int salary: salaries){
            sumOfSalaries+=salary;
            minSalary = Math.min(minSalary, salary);
            maxSalary = Math.max(maxSalary, salary);
        }
        
        return (sumOfSalaries-minSalary-maxSalary)/(salaries.length-2);
    }
}