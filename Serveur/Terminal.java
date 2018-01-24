package Serveur;

public class Terminal {
	public static final String SAVE_CURSOR = "\u001B[s";
	public static final String RESTORE_CURSOR = "\u001B[u";
	public static final String CURSOR_HORIZONTAL_ABSOLUTE = "\u001B[G";
	public static final String CURSOR_PREVIOUS_LINE = "\u001B[F";
	public static final String INSERT_LINE = "\u001B[L";
	public static final String CURSOR_DOWN = "\u001B[B";
	public static final String SCROLL_UP = "\u001B[S";
	public static final String SCROLL_DOWN = "\u001B[T";
	public static final String DELETE_LINE = "\u001B[M";
	public static final String BOLD_FONT = "\u001B[1m";
	public static final String NORMAL_FONT = "\u001B[22m";
	
	public static void out(String message){
    	System.out.print(SAVE_CURSOR);
    	System.out.print("\n");
    	System.out.print(CURSOR_PREVIOUS_LINE);
    	System.out.print(INSERT_LINE);
    	System.out.print(CURSOR_HORIZONTAL_ABSOLUTE);
		System.out.print(message);
		System.out.print(RESTORE_CURSOR);
		System.out.print(CURSOR_DOWN);
	}
	
	public static void out(String message, String color){
    	System.out.print(SAVE_CURSOR);
    	System.out.print("\n");
    	System.out.print(CURSOR_PREVIOUS_LINE);
    	System.out.print(INSERT_LINE);
    	System.out.print(CURSOR_HORIZONTAL_ABSOLUTE);
    	System.out.print(color);
		System.out.print(message);
		System.out.print(Color.RESET);
		System.out.print(RESTORE_CURSOR);
		System.out.print(CURSOR_DOWN);
	}
	
//	public static void out2(String message) {
//		in.printString("\r");
//
//	    in.printString(text + "\n");
//
//	    in.redrawLine();
//	    in.flushConsole();
//	}
}
