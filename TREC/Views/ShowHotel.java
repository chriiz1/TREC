package Views;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Controllers.HotelController;
import Models.Category;
import Models.Evaluation;
import Models.Hotel;
import Models.TREC;
import Models.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ShowHotel extends JFrame {

	private JPanel contentPane;
	public JButton btnRate = new JButton("Rate Hotel");

	public ShowHotel(Hotel hotel) {
		
		if(TREC.getInstance().getCurrentLoggedInUser() == null || TREC.getInstance().getCurrentLoggedInUser().getIs_admin())
			btnRate.setVisible(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setPreferredSize(new Dimension(450, 620));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Name = new JLabel(hotel.getName());
		lbl_Name.setFont(new Font("Dialog", Font.BOLD, 18));
		lbl_Name.setBounds(12, 12, 234, 25);
		contentPane.add(lbl_Name);
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDestination.setBounds(12, 45, 130, 15);
		contentPane.add(lblDestination);
		
		JLabel lbl_Destination = new JLabel(hotel.getDestination().getName());
		lbl_Destination.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Destination.setBounds(120, 45, 150, 15);
		contentPane.add(lbl_Destination);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCountry.setBounds(12, 66, 86, 15);
		contentPane.add(lblCountry);
		
		JLabel lbl_Country = new JLabel(hotel.getDestination().getCountry());
		lbl_Country.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Country.setBounds(120, 66, 150, 15);
		contentPane.add(lbl_Country);
		
		JLabel lblAdddress = new JLabel("Address:");
		lblAdddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAdddress.setBounds(12, 87, 86, 15);
		contentPane.add(lblAdddress);
		
		JLabel lbl_Adddress = new JLabel(hotel.getAddress());
		lbl_Adddress.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Adddress.setBounds(120, 87, 350, 15);
		contentPane.add(lbl_Adddress);
		
		JLabel lblStars = new JLabel("Stars:");
		lblStars.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStars.setBounds(12, 108, 86, 15);
		contentPane.add(lblStars);
		
		String stars = "";
		for(int i = 0; i < hotel.getStars();i++)
			stars += "★";
		
		JLabel lbl_Stars = new JLabel(stars);
		lbl_Stars.setFont(new Font("Dialog", Font.BOLD, 14));
		lbl_Stars.setBounds(120, 108, 150, 15);
		contentPane.add(lbl_Stars);
		
		
		JLabel lblActivities = new JLabel("Activities:");
		lblActivities.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblActivities.setBounds(12, 149, 101, 15);
		contentPane.add(lblActivities);
		
		JPanel panel_activities = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_activities.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_activities.setLayout(new GridLayout(5,4));
		panel_activities.setBounds(12, 167, 500, 100);
		
		for(Map.Entry<String, Integer> entry : hotel.getActivities().entrySet())
		{
			JPanel activity = new JPanel();
			activity.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 5));
			
			Category temp_activity = new Category(entry.getKey(), entry.getValue());
			JLabel temp_label = new JLabel(temp_activity.getName() + ": " + temp_activity.getValue());
			activity.add(temp_label);
			panel_activities.add(temp_label);
		}
		contentPane.add(panel_activities);
				
		
		btnRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				HotelController.rateHotel(hotel);
				ShowHotel.this.dispose();
			}
		});
		btnRate.setBounds(290, 270, 120, 25);
		contentPane.add(btnRate);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Dialog", Font.BOLD, 16));
		lblComments.setBounds(12, 270, 234, 15);
		contentPane.add(lblComments);
		
        JPanel comment_pane = new JPanel();
        comment_pane.setLayout(new GridLayout(hotel.getEvaluations().size(),1));
        
        for (int i = 0; i < hotel.getEvaluations().size(); i++)
        {
    		JPanel evaluation = new JPanel();
    		Evaluation currentEval = hotel.getEvaluations().get(i);
    		User user = currentEval.getUser();
    		String customername = user.getFirstname() == null || user.getLastName() == null ? user.getEMail().toString()
			  : user.getFirstname() + " " + user.getLastName();
    		evaluation.add(new JLabel("<html><u>Name:</u> " + customername +
    								  "&emsp;&emsp;<u>Nights:</u> "+ currentEval.getNightsSpend() + "</html>"));
    		evaluation.add(Box.createRigidArea(new Dimension(0, 15)));
    		String rating = "<html><u>Rating:</u> </br>";
    		int k = 0;
    		
    		for(int j = 0; j < currentEval.getActivities().size(); j++)
    		{	
    			Category currentActivity = currentEval.getActivities().get(j);
    			
    			rating += currentActivity.getName() + " " + currentActivity.getValue();
    			if(j < currentEval.getActivities().size()-1)
    				rating += ", ";
    			k++;
    			if(k == 3)
    			{
    				rating += "<br/>";
    				k = 0;
    			}
    		}
    		
    		rating += "</html>";
    		evaluation.add(new JLabel(rating));
    		evaluation.add(Box.createRigidArea(new Dimension(0, 15)));
    		evaluation.add(new JLabel("<html><u>Comment:</u> " + currentEval.getComment()+"</html>"));
    		evaluation.add(Box.createRigidArea(new Dimension(0, 15)));
    		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    		String date = currentEval.getDate();
    		evaluation.add(new JLabel("<html><i>"+date+"</i></html>"));
    		evaluation.setLayout(new BoxLayout(evaluation, BoxLayout.Y_AXIS));
    		evaluation.setBounds(5, 5, 30, 30);
    		comment_pane.add(evaluation);
    		evaluation.setBorder(new CompoundBorder(
    			    BorderFactory.createLineBorder(Color.black),
    			    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        }
        
        JScrollPane scrollPane = new JScrollPane(comment_pane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBounds(12, 300, 400, 300);
        
        contentPane.add(scrollPane);
        pack();
        setVisible(true);
		

	}
	public ShowHotel() {}
}
