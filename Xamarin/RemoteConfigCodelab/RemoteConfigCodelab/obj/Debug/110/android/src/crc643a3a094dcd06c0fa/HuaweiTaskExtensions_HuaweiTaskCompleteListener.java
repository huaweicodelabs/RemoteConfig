package crc643a3a094dcd06c0fa;


public class HuaweiTaskExtensions_HuaweiTaskCompleteListener
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		com.huawei.hmf.tasks.OnCompleteListener
{
/** @hide */
	public static final String __md_methods;
	static {
		__md_methods = 
			"n_onComplete:(Lcom/huawei/hmf/tasks/Task;)V:GetOnComplete_Lcom_huawei_hmf_tasks_Task_Handler:Huawei.Hmf.Tasks.IOnCompleteListenerInvoker, Huawei.Hmf.Tasks\n" +
			"";
		mono.android.Runtime.register ("Huawei.Hms.Opendevice.HuaweiTaskExtensions+HuaweiTaskCompleteListener, Huawei.Hms.Opendevice", HuaweiTaskExtensions_HuaweiTaskCompleteListener.class, __md_methods);
	}


	public HuaweiTaskExtensions_HuaweiTaskCompleteListener ()
	{
		super ();
		if (getClass () == HuaweiTaskExtensions_HuaweiTaskCompleteListener.class)
			mono.android.TypeManager.Activate ("Huawei.Hms.Opendevice.HuaweiTaskExtensions+HuaweiTaskCompleteListener, Huawei.Hms.Opendevice", "", this, new java.lang.Object[] {  });
	}


	public void onComplete (com.huawei.hmf.tasks.Task p0)
	{
		n_onComplete (p0);
	}

	private native void n_onComplete (com.huawei.hmf.tasks.Task p0);

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
