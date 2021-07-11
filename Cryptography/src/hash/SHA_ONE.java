package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class SHA_ONE {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			SHA_ONE myOne = new SHA_ONE();
			String file;
			if(args.length != 0)file = args[0];
			else file = in.nextLine();
			String digest = myOne.generate_CSE493(file);
			System.out.println(digest+"\nAlso check hvalue.txt");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();

    }
	private static int h1,h2,h3,h4,h5,k1,k2,k3,k4;
    private String file_dir;
    private String digest;
    SHA_ONE(){
    	h1 = 0x67452301;
        h2 = 0xEFCDAB89;
        h3 = 0x98BADCFE;
        h4 = 0x10325476;
        h5 = 0xC3D2E1F0;
        
        k1 = 0x5A827999;
        k2 = 0x6ED9EBA1;
        k3 = 0x8F1BBCDC;
        k4 = 0xCA62C1D6;
    }
    
    public String generate_CSE493(String file_path) throws IOException {
    	this.file_dir = file_path;
    	InputStream fis = new FileInputStream(file_path);
		byte[] hold = fis.readAllBytes();
    	calculateMod(hold);
    	fis.close();
    	storeInto("hvalue.txt");
    	return digest;
    }
    
    private void storeInto(String storage_dir) throws IOException {
		File file = new File(storage_dir);
		file.createNewFile();
		PrintWriter writer = new PrintWriter(file);
		writer.println("File:-->> "+this.file_dir+"\nDigest->> "+digest);
		writer.close();
    }

    private void calculateMod(byte[] bytes) {
        StringBuilder bina = new StringBuilder();
        for (byte b : bytes) {
            int i, val = b;
            for (i = 0; i < 8; i++) {
                bina.append( (val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            bina.append(' ');
        }
        String bin = bina.toString();
        int binLen = bytes.length * 8 - 8;
        String endBitLength = calMsgLen(binLen+8);
        int temp = binLen % 512;
        int binaryZeros =  (432 - temp < 0) ? ((512 - temp) + 440 + temp + 64) : 432 - temp;
        generateMsg(bin.replaceAll("\\s+", ""), "10000000", binaryZeros, endBitLength); //creates the 512 bit message

    }
    
    private String calMsgLen(int bitLength) {
        String tempBitsLength = Integer.toBinaryString(bitLength);
        StringBuilder sb = new StringBuilder(tempBitsLength);
        int temp = 64 - tempBitsLength.length();
        while (temp-- > 0) sb.insert(0, 0);
        return sb.toString();
    }

    private String generateMsg(String message, String paddedOne, int zeros, String endLength) {

        StringBuilder messageBinary = new StringBuilder(message);
        messageBinary.insert(messageBinary.toString().length(), paddedOne);
        while (zeros > 0) {
            messageBinary.insert(messageBinary.toString().length(), 0);
            zeros--;
        }
        messageBinary.insert(messageBinary.toString().length(), endLength);
        String m = printMessage(messageBinary.toString());
        m = m.replaceAll("\\s+", "");
        int[] mArray = new int[m.toString().length()/32];

        for (int i = 0; i < m.toString().length(); i+=32) {
            mArray[i/32] = Integer.valueOf(m.substring(i+1, i+32),2);
            if(m.charAt(i) == '1'){
                mArray[i/32] |= 0X80000000;
            }
        }
        hash(mArray);
        return messageBinary.toString();
    }

    private String printMessage(String message) {

        StringBuilder sb = new StringBuilder(message);
        int num = message.length();

        while (num > 0) {
            if (num % 32 == 0) {
                sb.insert(num, " ");
            }
            num--;
        }

        return sb.toString();

    }

    private int leftrotate(int x, int shift) {
        return ((x << shift) | (x >>> (32 - shift))); 
    }

    private String hash(int[] z) {
        int integer_count = z.length;
        int[] arr = new int[80];
        int j = 0;

        for(int i = 0; i < integer_count; i += 16) {
        	int A = h1, B = h2, C = h3, D = h4, E = h5, t = 0;
            for(j = 0; j <= 15; j++)
            	arr[j] = z[j+i];
            for ( j = 16; j <= 79; j++ ) {
            	arr[j] = leftrotate(arr[j - 3] ^ arr[j - 8] ^ arr[j - 14] ^ arr[j - 16], 1);
            }

            for ( int x = 0; x <= 19; x++ ) {
                t = leftrotate(A,5)+((B&C)|((~B)&D))+E+arr[x]+k1;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int b = 20; b <= 39; b++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+arr[b]+k2;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for (int c = 40; c <= 59; c++ ) {
                t = leftrotate(A,5)+((B&C)|(B&D)|(C&D))+E+arr[c]+k3;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }
            for ( int d = 60; d <= 79; d++ ) {
                t = leftrotate(A,5)+(B^C^D)+E+arr[d]+k4;
                E=D; D=C; C=leftrotate(B,30); B=A; A=t;
            }

            h1+=A; h2+=B; h3+=C; h4+=D; h5+=E;

        }

        String h1Length = Integer.toHexString(h1);
        String h2Length = Integer.toHexString(h2);
        String h3Length = Integer.toHexString(h3);
        String h4Length = Integer.toHexString(h4);
        String h5Length = Integer.toHexString(h5);

        if(h1Length.length() < 8) {
            StringBuilder h1L = new StringBuilder(h1Length);
            h1L.insert(0,0);
            h1Length = h1L.toString();
        } else if(h2Length.length() < 8) {
            StringBuilder h2L = new StringBuilder(h2Length);
            h2L.insert(0,0);
            h2Length = h2L.toString();
        } else if(h3Length.length() < 8) {
            StringBuilder h3L = new StringBuilder(h3Length);
            h3L.insert(0,0);
            h3Length = h3L.toString();
        } else if(h4Length.length() < 8) {
            StringBuilder h4L = new StringBuilder(h4Length);
            h4L.insert(0,0);
            h4Length = h4L.toString();
        } else if(h5Length.length() < 8) {
            StringBuilder h5L = new StringBuilder(h5Length);
            h5L.insert(0,0);
            h5Length = h5L.toString();
        }

        this.digest = h1Length + h2Length + h3Length + h4Length + h5Length;
        return null;
    }
}