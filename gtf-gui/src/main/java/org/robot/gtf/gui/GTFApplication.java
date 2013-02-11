/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.robot.gtf.gui;

import java.util.List;

import org.robot.gtf.service.ProjectService;
import org.robot.gtf.service.TestScenarioService;
import org.robot.gtf.service.impl.dummy.DummyProjectService;
import org.robot.gtf.service.impl.dummy.DummyTestScenarioService;
import org.robot.gtf.service.to.ProjectTO;
import org.robot.gtf.service.to.TestScenarioDefinitionTO;
import org.robot.gtf.service.to.TestcaseParametersTO;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.Runo;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class GTFApplication extends Application
{
    private Window window;
    
    private Window newProjectWindow;

    private Window aboutWindow;

    private Button buttonNewTestProject;
    
    private Button buttonOpenTestProject;
    
    private Button buttonSettings;
    
    private Button buttonHelp;

    private Table table = new Table("Testcases");
    
    private Table table2 = null;
    
    private Button addRowButton;
    
    private ListSelect scenariosListSelect = new ListSelect("Test Scenarios");
    
    @Override
    public void init()
    {
    	buildNewProjectWindow();
    	buildAboutWindow();
    	buildMenuButtons();

        HorizontalLayout container = new HorizontalLayout();
        
        window = new Window(TextResources.getAppName() + " - " + TextResources.getAppVersion());
        window.setTheme("runo");
        window.setContent(container);
        setMainWindow(window);

        // Main Menu
        VerticalLayout menuContainer = new VerticalLayout();
        menuContainer.addComponent(buttonNewTestProject);
        menuContainer.addComponent(buttonOpenTestProject);
        menuContainer.addComponent(buttonSettings);
        menuContainer.addComponent(buttonHelp);
        menuContainer.setMargin(true, true, false, true);
        
        container.addComponent(menuContainer);


        // Main Content
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setMargin(true, false, false, false);

        // Heading Layout
        VerticalLayout headingContent = new VerticalLayout();
        headingContent.setStyleName("v-window-header ");

        Label heading = new Label(TextResources.getAppName() + " - " + TextResources.getAppVersion());
        heading.setStyleName("v-label-h1");
        headingContent.addComponent(heading);

        mainContent.addComponent(headingContent);
        
        // Testcase table and Scenario selection
        HorizontalLayout testcases = new HorizontalLayout();
        table.setVisible(false);
        
        // Selection list with scenarios
        scenariosListSelect.setNullSelectionAllowed(false);        
        scenariosListSelect.setImmediate(true);
        scenariosListSelect.setVisible(false);

        testcases.setSpacing(true);
        testcases.addComponent(table);
        testcases.addComponent(scenariosListSelect);
        
        mainContent.addComponent(testcases);

        // Table Button
        addRowButton = new Button("Add Testcase", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	if (table2 == null) {
            		table.addItem();
            	} else {
            		table2.addItem();
            	}
            }
        });
        addRowButton.addStyleName(Runo.BUTTON_SMALL);
        addRowButton.addStyleName(Runo.BUTTON_DEFAULT);
        addRowButton.setVisible(false);


        mainContent.addComponent(addRowButton);
        
        
        container.addComponent(mainContent);
    }
    
    

    private void buildMenuButtons() {

    	// New Test Project
    	buttonNewTestProject = new Button("New Test Project", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                window.addWindow(newProjectWindow);
            }
        });
    	buttonNewTestProject.addStyleName(Runo.BUTTON_BIG);
    	buttonNewTestProject.addStyleName(Runo.BUTTON_DEFAULT);
    	buttonNewTestProject.setIcon(new ThemeResource("../runo/icons/32/folder-add.png"));
    	buttonNewTestProject.setWidth(230, Window.UNITS_PIXELS);
    	
    	
    	// Open Test Project
    	buttonOpenTestProject = new Button("Open Test Project");
    	buttonOpenTestProject.addStyleName(Runo.BUTTON_BIG);
    	buttonOpenTestProject.addStyleName(Runo.BUTTON_DEFAULT);
    	buttonOpenTestProject.setIcon(new ThemeResource("../runo/icons/32/folder.png"));
    	buttonOpenTestProject.setWidth(230, Window.UNITS_PIXELS);

    	// Settings
    	buttonSettings = new Button("Settings");
    	buttonSettings.addStyleName(Runo.BUTTON_BIG);
    	buttonSettings.addStyleName(Runo.BUTTON_DEFAULT);
    	buttonSettings.setIcon(new ThemeResource("../runo/icons/32/settings.png"));
    	buttonSettings.setWidth(230, Window.UNITS_PIXELS);

    	// Help
    	buttonHelp = new Button("Help");
    	buttonHelp.addStyleName(Runo.BUTTON_BIG);
    	buttonHelp.addStyleName(Runo.BUTTON_DEFAULT);
    	buttonHelp.setIcon(new ThemeResource("../runo/icons/32/help.png"));
    	buttonHelp.setWidth(230, Window.UNITS_PIXELS);
    	

    }
    

    private void buildNewProjectWindow() {

    	// Reading in Project Data
    	ProjectService projectService = new DummyProjectService();
    	List<ProjectTO> projectList = projectService.read();
    	
    	final ListSelect projectSelect = new ListSelect("For main project (please select)");
    	
    	String firstElement = null;
    	for (ProjectTO projectTO : projectList) {
    		projectSelect.addItem(projectTO.getName());
    		if (firstElement == null) {
    			firstElement = projectTO.getName(); 
    		}
    	}

    	projectSelect.setNullSelectionAllowed(false);
    	if (firstElement != null) {
    		projectSelect.select(firstElement);
    	}
    	
    	// Create the window...
    	newProjectWindow = new Window("New Testcase Project");
    	newProjectWindow.setModal(true);
    	newProjectWindow.setResizable(true);
    	newProjectWindow.setParent(getWindow());

        // Configure the window layout; by default a VerticalLayout
        VerticalLayout layout = (VerticalLayout) newProjectWindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);

        // Input field for the project name
        final TextField projectName = new TextField("Name: ");
        newProjectWindow.addComponent(projectName);

        newProjectWindow.addComponent(projectSelect);

        // Definition of buttons
        HorizontalLayout buttons = new HorizontalLayout();
        
        Button loadButton = new Button("Load", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
            	String projectNameValue = (String) projectName.getValue();
            	String projectSelectValue = (String) projectSelect.getValue();
            	
            	if (projectNameValue == null || projectNameValue.isEmpty()) {
            		getWindow().showNotification(
                            "Mandatory value missing",
                            "Please give a name for the new Testcase Project",
                            Notification.TYPE_ERROR_MESSAGE);

            	} else {
            		getWindow().removeWindow(newProjectWindow);
            		createTestdataProject(projectSelectValue, projectNameValue);
            	}
            }
        });
        
        Button abortButton = new Button("Abort", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                (newProjectWindow.getParent()).removeWindow(newProjectWindow);
            }
        });
        
        buttons.addComponent(loadButton);
        buttons.addComponent(abortButton);
        
        layout.addComponent(buttons);
    }

    
    
    private void buildAboutWindow() {
    	
    	
    	// Create the window...
    	aboutWindow = new Window("About");
    	aboutWindow.setModal(true);
    	aboutWindow.setResizable(false);
    	aboutWindow.setWidth(350, Window.UNITS_PIXELS);
    	aboutWindow.setHeight(180, Window.UNITS_PIXELS);

        // Configure the window layout; by default a VerticalLayout
        VerticalLayout layout = (VerticalLayout) aboutWindow.getContent();
        layout.setMargin(true);
        layout.setSpacing(true);

        // Add some content; a label and a close-button
        Label message = new Label(TextResources.getAboutText());
        message.setContentMode(Label.CONTENT_XHTML);
        aboutWindow.addComponent(message);
        
        Button close = new Button("Close", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                (aboutWindow.getParent()).removeWindow(aboutWindow);
            }
        });
        
        // The components added to the window are actually added to the window's
        // layout; you can use either. Alignments are set using the layout
        layout.addComponent(close);
        layout.setComponentAlignment(close, Alignment.TOP_RIGHT);
    }


    
    public void createTestdataProject(String mainProjectName, String name) {

    	System.out.println("Creating Project");
    	System.out.println("Main Name: " + mainProjectName);
    	System.out.println("Name: " + name);
    	
    	
    	TestScenarioService testScenarioService = new DummyTestScenarioService();
    	List<TestScenarioDefinitionTO> scenarios = testScenarioService.read(mainProjectName);

    	scenariosListSelect.removeAllItems();
    	String firstTestScenario = null;
    	for (TestScenarioDefinitionTO scenario : scenarios) {
    		scenariosListSelect.addItem(scenario.getName());
    		if (firstTestScenario == null) {
    			firstTestScenario = scenario.getName();
    		}
    	}
    	scenariosListSelect.select(firstTestScenario);
        scenariosListSelect.setVisible(true);
    	
    	// TODO Save some project stuff here later
    	
    	
    	// Update table and buttons
   		refreshTable(mainProjectName, firstTestScenario);
        addRowButton.setVisible(true);
   		
    }
    
    
    public void refreshTable(String mainProjectName, String scenarioName) {
    	
    	TestScenarioService testScenarioService = new DummyTestScenarioService();
    	List<TestcaseParametersTO> scenarioParameters = testScenarioService.readParameters(mainProjectName, scenarioName);
    	
    	if (table2 == null) {
    	
	    	table2 = new Table("Testcases");
	        table2.setStyleName("iso3166");
	        table2.setWidth("100%");
	        table2.setHeight("170px");
	        table2.setEditable(true);
	    	
	    	for (TestcaseParametersTO testcaseParameterTO : scenarioParameters) {
	            table2.addContainerProperty(testcaseParameterTO.getName(), String.class,  null);    		
	    	}
	    	
	    	HorizontalLayout parent = (HorizontalLayout) table.getParent();
	    	parent.replaceComponent(table, table2);
	    	
	    	table = null;
    	} else {

	    	table = new Table("Testcases");
	        table.setStyleName("iso3166");
	        table.setWidth("100%");
	        table.setHeight("170px");
	        table.setEditable(true);
	    	
	    	for (TestcaseParametersTO testcaseParameterTO : scenarioParameters) {
	            table.addContainerProperty(testcaseParameterTO.getName(), String.class,  null);    		
	    	}
	    	
	    	HorizontalLayout parent = (HorizontalLayout) table2.getParent();
	    	parent.replaceComponent(table2, table);
	    	
	    	table2 = null;
    	}
    }
    
	public Window getWindow() {
		return window;
	}
}
