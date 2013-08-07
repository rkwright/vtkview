package com.geofx.vtk.plugin;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import vtk.vtkNativeLibrary;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

	// The plug-in ID
	public static final String PLUGIN_ID = "VTK_View";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator()
	{
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception
	{
		super.start(context);

		System.out.println("java.library.path is " + System.getProperty("java.library.path"));

		loadLibraries();
	}

	private void loadLibraries()
	{
		if (!vtkNativeLibrary.LoadAllNativeLibraries())
		{
			for (vtkNativeLibrary lib : vtkNativeLibrary.values())
			{
				if (!lib.IsLoaded())
				{
					System.out.println(lib.GetLibraryName() + " not loaded");
				}
			}
		}
		vtkNativeLibrary.DisableOutputWindow(null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception
	{
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault()
	{
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
