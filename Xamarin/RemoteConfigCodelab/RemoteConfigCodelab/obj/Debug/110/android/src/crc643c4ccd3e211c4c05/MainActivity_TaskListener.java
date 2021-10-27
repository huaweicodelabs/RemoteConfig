package crc643c4ccd3e211c4c05;


public class MainActivity_TaskListener
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.huawei.hmf.tasks.OnSuccessListener,
		com.huawei.hmf.tasks.OnFailureListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onSuccess:(Ljava/lang/Object;)V:GetOnSuccess_Ljava_lang_Object_Handler:Huawei.Hmf.Tasks.IOnSuccessListenerInvoker, Huawei.Hmf.Tasks\n" +
			"n_onFailure:(Ljava/lang/Exception;)V:GetOnFailure_Ljava_lang_Exception_Handler:Huawei.Hmf.Tasks.IOnFailureListenerInvoker, Huawei.Hmf.Tasks\n" +
			"";
		mono.android.Runtime.register ("RemoteConfigCodelab.MainActivity+TaskListener, RemoteConfigCodelab", MainActivity_TaskListener.class, __md_methods);
	}


	public MainActivity_TaskListener ()
	{
		super ();
		if (getClass () == MainActivity_TaskListener.class)
			mono.android.TypeManager.Activate ("RemoteConfigCodelab.MainActivity+TaskListener, RemoteConfigCodelab", "", this, new java.lang.Object[] {  });
	}

	public MainActivity_TaskListener (crc643c4ccd3e211c4c05.MainActivity p0)
	{
		super ();
		if (getClass () == MainActivity_TaskListener.class)
			mono.android.TypeManager.Activate ("RemoteConfigCodelab.MainActivity+TaskListener, RemoteConfigCodelab", "RemoteConfigCodelab.MainActivity, RemoteConfigCodelab", this, new java.lang.Object[] { p0 });
	}


	public void onSuccess (java.lang.Object p0)
	{
		n_onSuccess (p0);
	}

	private native void n_onSuccess (java.lang.Object p0);


	public void onFailure (java.lang.Exception p0)
	{
		n_onFailure (p0);
	}

	private native void n_onFailure (java.lang.Exception p0);

	private java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}
