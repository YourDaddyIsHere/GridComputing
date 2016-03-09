import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
public class Node extends UnicastRemoteObject implements INode {
	
	String myResource_Manager = "";
	int myStatus = 1;    //1 means idle, 0 means busy
	static String myurl = "rmi://localhost:7001/Node1";
	static int myid = 1;
	
    // ���ʵ�ֱ�����һ����ʽ�Ĺ��캯��������Ҫ�׳�һ��RemoteException�쳣  
    protected Node() throws RemoteException {
        super();
        
    }
    /**
     * ˵����������Ե�ҵ����
     */
    private static final long serialVersionUID = 4077329331699640331L;
    public String sayHello(String name) throws RemoteException {
        return "Hello " + name + " ^_^ ";
    }
    
    
    //this function is for Resource Manager to test whether the stub from
    //this node works.
    public void testStub()
    {
    	System.out.println("the stub of node has been used");
    }
    
    
    public static void main(String[] args) {
        try {
            INode Node = new Node();
            LocateRegistry.createRegistry(7001);
            java.rmi.Naming.rebind("rmi://localhost:7001/Node1", Node);
            IResource_Manager RM = (IResource_Manager)Naming.lookup("rmi://localhost:6001/RM1");
            RM.getStub(myurl,myid);
            RM.testStub();
            
            System.out.print("Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
