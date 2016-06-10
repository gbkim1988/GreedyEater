package kr.greedyeater.gadget.util;

public class GlobalProxyJNI {
	static {
		// Load Native Library at runtime
		// Proxy.dll (windows) or Proxy.so (Unixes)
		if (System.getProperty("sun.arch.data.model").contains("64")){
			System.loadLibrary("Proxy64");
			System.out.println("Proxy 64 bit Dynamic Module Loaded.");
		}
		else{
			System.loadLibrary("Proxy32");
			System.out.println("Proxy 32 bit Dynamic Module Loaded.");
		}
	}
	public static void init(){}
	public static native void activateGlobalProxy(String Address);
	public static native void deactivateGlobalProxy();
	
}
