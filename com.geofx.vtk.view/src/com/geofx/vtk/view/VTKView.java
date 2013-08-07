package com.geofx.vtk.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import vtk.vtkActor;
import vtk.vtkConeSource;
import vtk.vtkPolyDataMapper;
import vtk.rendering.awt.vtkAwtComponent;

/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. 
 */

public class VTKView extends ViewPart 
{	

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent)
	{	
		System.setProperty("sun.awt.noerasebackground","true");

		// instantiate the canvas

		// we can't use the default Composite because using the AWT bridge
		// requires that it have the property of SWT.EMBEDDED
		Composite composite = new Composite(parent, SWT.EMBEDDED);
		// set the layout so our canvas fills the whole control
		composite.setLayout(new FillLayout());

		// create the special frame bridge to AWT
		java.awt.Frame awtFrame = SWT_AWT.new_Frame(composite);

		// build VTK Pipeline
		vtkConeSource cone = new vtkConeSource();
		cone.SetResolution(20);
		cone.SetHeight(0.3);
		cone.SetRadius(0.1);

		vtkPolyDataMapper coneMapper = new vtkPolyDataMapper();
		coneMapper.SetInputConnection(cone.GetOutputPort());

		vtkActor coneActor = new vtkActor();
		coneActor.SetMapper(coneMapper);

		// VTK rendering part
		vtkAwtComponent awtWidget = new vtkAwtComponent();
		awtWidget.getRenderer().AddActor(coneActor);

		// finally, add our canvas as a child of the frame
		awtFrame.add(awtWidget.getComponent());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus()
	{

	}
}