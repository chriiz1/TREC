import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interessen extends JFrame {

	private JPanel contentPane;
	private JButton btn_submit;
	List<Activity> Activities = new ArrayList();
	Map<String,JSlider> Sliders = new HashMap<String,JSlider>();
	Map<String,JLabel>Labels = new HashMap<String,JLabel>();
	private JSlider slider;
	private JLabel lblNewLabel_1;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interessen frame = new Interessen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interessen() {
		fillThemes();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(10, -13, 320, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Speichern");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Interessen.this.dispose();
			}
		});
		btnNewButton.setBounds(12, 191, 114, 25);
		contentPane.add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("Interessen festlegen");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel.setBounds(12, 12, 257, 22);
		contentPane.add(lblNewLabel);
		listThemes();
	}
	public void fillThemes()
	{
		Activities.add(new Activity("Lifestyle"));
		Activities.add(new Activity("Sport"));
		Activities.add(new Activity("Abenteuer"));
		Activities.add(new Activity("Familie"));
		Activities.add(new Activity("Kultur"));
	}
	public void listThemes()
	{
		for(int i = 0; i < Activities.size(); i++)
		{
			String activity_name = Activities.get(i).name;
			
			lblNewLabel_1 = new JLabel(Activities.get(i).name);
			lblNewLabel_1.setBounds(12, 53+i*20, 150, 15);
			contentPane.add(lblNewLabel_1);
					
			slider = new JSlider();
			slider.setName("slider_" + activity_name);
			slider.setMaximum(10);
			slider.setMinimum(0);
			slider.setBounds(160, 53+i*20, 114, 16);
			slider.setValue(Activities.get(i).value);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					JLabel temp_label = Labels.get(activity_name);
					JSlider temp_slider = Sliders.get(activity_name);
					temp_label.setText(String.valueOf(temp_slider.getValue()));
				}
			});
			contentPane.add(slider);
			Sliders.put(activity_name, slider);
			
			label = new JLabel(String.valueOf(slider.getValue()));
			label.setBounds(300, 53+i*20, 80, 15);
			contentPane.add(label);
			Labels.put(Activities.get(i).name, label);
		}	
	}
}
