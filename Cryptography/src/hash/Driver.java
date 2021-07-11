package hash;


public class Driver {
	public static void main(String[] args) {
		try {
			SHA_ONE myOne = new SHA_ONE();
			String file = "C:\\Users\\Artful\\Desktop\\hold.txt";
			String digest = myOne.generate_CSE493(file);
			System.out.println(digest);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

    }
}
