package presentation;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import businessLogic.FlightManager;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import domain.ConcreteFlight;

public class FlightBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane = null;
	private JLabel lblDepartCity = new JLabel("Departing city:");
	private JLabel lblArrivalCity = new JLabel("Arrival City");
	private JLabel lblYear = new JLabel("Year:");
	private JLabel lblRoomType = new JLabel("Room Type:");
	private JLabel lblMonth = new JLabel("Month:");
	private JLabel lblDay = new JLabel("Day:");;
	private JLabel jLabelResult = new JLabel();
	private JLabel searchResult = new JLabel();
	private JTextField day = null;
	private JComboBox<String> months = null;
	private DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();

	private JTextField year = null;

	private JRadioButton bussinesTicket = null;
	private JRadioButton firstTicket = null;
	private JRadioButton touristTicket = null;

	private ButtonGroup fareButtonGroup = new ButtonGroup();

	private JButton lookforFlights = null;
	// private DefaultListModel<ConcreteFlight> flightInfo = new
	// DefaultListModel<ConcreteFlight>();

	private JList<ConcreteFlight> flightList = null;
	private JButton bookFlight = null;

	private Collection<ConcreteFlight> concreteFlightCollection;

	private FlightManager businessLogic; // @jve:decl-index=0:;

	private ConcreteFlight selectedConcreteFlight;

	// nerea
	private DefaultComboBoxModel DepartingCity = new DefaultComboBoxModel();
	private DefaultComboBoxModel ArrivalCity = new DefaultComboBoxModel();
	private DefaultComboBoxModel HegaldiZerrenda = new DefaultComboBoxModel();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightBooking frame = new FlightBooking();
					frame.setBusinessLogic(new FlightManager());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Custom operations
	public void setBusinessLogic(FlightManager g) {
		businessLogic = g;
	}

	private Date newDate(int year, int month, int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * Create the frame
	 * 
	 * @return void
	 */
	private FlightBooking() {

		setTitle("Book Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDepartCity = new JLabel("Depart City");
		lblDepartCity.setBounds(21, 11, 56, 16);
		contentPane.add(lblDepartCity);

		lblYear = new JLabel("Year:");
		lblYear.setBounds(21, 62, 33, 16);
		contentPane.add(lblYear);

		lblMonth = new JLabel("Month:");
		lblMonth.setBounds(117, 62, 50, 16);
		contentPane.add(lblMonth);

		months = new JComboBox<String>();
		months.setBounds(163, 58, 116, 27);
		contentPane.add(months);
		months.setModel(monthNames);

		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		months.setSelectedIndex(1);

		lblDay = new JLabel("Day:");
		lblDay.setBounds(291, 62, 38, 16);
		contentPane.add(lblDay);

		day = new JTextField();
		day.setText("23");
		day.setBounds(331, 57, 50, 26);
		contentPane.add(day);
		day.setColumns(10);

		lblRoomType = new JLabel("Seat Type:");
		lblRoomType.setBounds(21, 242, 84, 16);
		contentPane.add(lblRoomType);

		bussinesTicket = new JRadioButton("Business");
		bussinesTicket.setEnabled(false);
		bussinesTicket.setSelected(true);
		fareButtonGroup.add(bussinesTicket);
		bussinesTicket.setBounds(99, 238, 101, 23);
		contentPane.add(bussinesTicket);

		firstTicket = new JRadioButton("First");
		firstTicket.setEnabled(false);
		fareButtonGroup.add(firstTicket);
		firstTicket.setBounds(202, 238, 77, 23);
		contentPane.add(firstTicket);

		touristTicket = new JRadioButton("Tourist");
		touristTicket.setEnabled(false);
		fareButtonGroup.add(touristTicket);
		touristTicket.setBounds(278, 238, 77, 23);
		contentPane.add(touristTicket);

		lookforFlights = new JButton("Look for Concrete Flights");
		lookforFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HegaldiZerrenda.removeAllElements();

				/*
				 * bookFlight.setEnabled(true); flightInfo.clear(); bookFlight.setText("");
				 */

				java.util.Date date = newDate(Integer.parseInt(year.getText()), months.getSelectedIndex(),
						Integer.parseInt(day.getText()));

				concreteFlightCollection = businessLogic.getConcreteFlights(DepartingCity.getSelectedItem().toString(),
						ArrivalCity.getSelectedItem().toString(), date);
				for (ConcreteFlight f : concreteFlightCollection)
					HegaldiZerrenda.addElement(f);
				if (concreteFlightCollection.isEmpty())
					searchResult.setText("No flights in that city in that date");
				else
					searchResult.setText("Choose an available flight in this list:");
			}

		});
		lookforFlights.setBounds(81, 90, 261, 40);
		contentPane.add(lookforFlights);

		jLabelResult = new JLabel("");
		jLabelResult.setBounds(109, 180, 243, 16);
		contentPane.add(jLabelResult);

		final JComboBox HegaldiZerrendacomboBox = new JComboBox();
		HegaldiZerrendacomboBox.setBounds(38, 174, 356, 22);
		HegaldiZerrendacomboBox.setModel(HegaldiZerrenda);
		contentPane.add(HegaldiZerrendacomboBox);

		HegaldiZerrendacomboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ConcreteFlight aukeratutakoHegaldia = (ConcreteFlight) HegaldiZerrenda.getSelectedItem();
				if (aukeratutakoHegaldia.getBusinessNumber() == 0) {
					bussinesTicket.setEnabled(false);

				} else {
					bussinesTicket.setEnabled(true);
				}
				if (aukeratutakoHegaldia.getFirstNumber() == 0) {
					firstTicket.setEnabled(false);

				} else {
					firstTicket.setEnabled(true);
					if (aukeratutakoHegaldia.getTouristNumber() == 0) {
						touristTicket.setEnabled(false);

					} else {
						touristTicket.setEnabled(true);

					}

				}

			}

		});

		bookFlight = new JButton("");
		bookFlight.setEnabled(false);
		bookFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConcreteFlight aukeratutakoHegaldia = (ConcreteFlight) HegaldiZerrenda.getSelectedItem();
				int num = 0;
				boolean error = false;
				String botoiMota = null;

				touristTicket.setEnabled(false);
				firstTicket.setEnabled(false);
				bussinesTicket.setEnabled(false);
				HegaldiZerrendacomboBox.setSelectedIndex(0);

				if (bussinesTicket.isSelected()) {
					botoiMota = "bussines";

				} else if (firstTicket.isSelected()) {
					botoiMota = "first";

				} else if (touristTicket.isSelected()) {
					botoiMota = "tourist";

				}
				businessLogic.bookSmart(aukeratutakoHegaldia, botoiMota);

				bookFlight.setText("Booked. #seat left: " + (num - 1));
				bookFlight.setEnabled(false);
				HegaldiZerrenda.addElement("                     ");
				HegaldiZerrendacomboBox.setSelectedIndex(HegaldiZerrendacomboBox.getItemCount() - 1);
				bussinesTicket.setEnabled(true);
				firstTicket.setEnabled(true);
				touristTicket.setEnabled(true);

			}

		});
		bookFlight.setBounds(31, 273, 399, 40);
		contentPane.add(bookFlight);

		year = new JTextField();
		year.setText("2023");
		year.setBounds(57, 57, 50, 26);
		contentPane.add(year);
		year.setColumns(10);

		lblArrivalCity.setBounds(21, 39, 84, 16);
		contentPane.add(lblArrivalCity);

		searchResult.setBounds(57, 130, 314, 16);
		contentPane.add(searchResult);

		// nere zatia

		JComboBox DeparCitycomboBox = new JComboBox();
		DeparCitycomboBox.setModel(DepartingCity);
		DeparCitycomboBox.setBounds(94, 8, 135, 22);
		DepartingCity.addElement("Barcelona");
		DepartingCity.addElement("Donostia");
		DepartingCity.addElement("Sevilla");
		contentPane.add(DeparCitycomboBox);

		JComboBox ArrivalCitycomboBox = new JComboBox();
		ArrivalCitycomboBox.setModel(ArrivalCity);
		ArrivalCitycomboBox.setBounds(94, 36, 135, 22);
		contentPane.add(ArrivalCitycomboBox);

		DeparCitycomboBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String mota = DepartingCity.getSelectedItem().toString();
				List<String> ArrivalCitiesLista = businessLogic.getArrivalCitiesFrom(mota);
				for (String i : ArrivalCitiesLista) {
					ArrivalCity.addElement(i);
				}

			}

		});

		touristTicket.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (touristTicket.isSelected() && touristTicket.isEnabled()) {
					bookFlight.setEnabled(true);
				} else {
					bookFlight.setEnabled(false);
				}
			}
		});
		firstTicket.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (firstTicket.isSelected() && firstTicket.isEnabled()) {
					bookFlight.setEnabled(true);
				} else {
					bookFlight.setEnabled(false);
				}
			}

		});
		bussinesTicket.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (bussinesTicket.isSelected() && bussinesTicket.isEnabled()) {
					bookFlight.setEnabled(true);
				} else {
					bookFlight.setEnabled(false);
				}
			}

		});

	}
}
// @jve:decl-index=0:visual-constraint="18,9"
