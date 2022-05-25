import java.util.*;

public class SearchEngine{
	public static void main(String args[]){
		Scanner input=new Scanner (System.in);

		String data="", sentence="BLANK";

		while(!sentence.equals("")){
			System.out.println("Input data(leave blank if done): ");
			sentence=input.nextLine().toLowerCase();
			data=data+"\n"+sentence;
		}
 		System.out.println(data);

 		//Splitting string into an array
		String[] dataArray = data.split("\\r?\\n");
		System.out.println(Arrays.toString(dataArray));

		System.out.print("Enter your search: ");
		String search=input.nextLine().toLowerCase();
		String[] searchArray = search.split(" ");

		String result="";
		//Search engine
		for(int count=1;count<dataArray.length;count++){
			int binary, binaryTemp, index=0;
			if(searchArray[index].equals("not")){
				index++;
				binary = dataArray[count].indexOf(searchArray[index]);
				if(binary == -1) {
					binary=1;
				} else {
					binary=0;
				}
				index++;
			}

			else if(searchArray[index].equals("and") || searchArray[index].equals("or")){
				binary=1;
			}else{
				binary = dataArray[count].indexOf(searchArray[index]);
				if(binary == -1) {
					binary=0;
				} else {
					binary=1;
				}
				index++;
			}
			while(index<searchArray.length){
				String op=searchArray[index];
				index++;
				if(searchArray[index].equals("not")){
					index++;
					binaryTemp = dataArray[count].indexOf(searchArray[index]);
					if(binaryTemp == - 1) {
						binaryTemp=1;
					} else {
						binaryTemp=0;
					}
				}
				else{binaryTemp = dataArray[count].indexOf(searchArray[index]);
					if(binaryTemp == - 1) {
						binaryTemp=0;
					} else {
						binaryTemp=1;
					}
				}
				if(op.equals("and")){
					binary=binary*binaryTemp;
				}
				else if(op.equals("or")){
					if(binary+binaryTemp==0){
						binary=0;
					} else{
						binary=1;
					}
				}
				index++;
			}
			System.out.print(binary+"	");
			if(binary==1){
				result=result+"	Doc"+count;
			}
		}
		System.out.println("Result: "+result);
	}
}