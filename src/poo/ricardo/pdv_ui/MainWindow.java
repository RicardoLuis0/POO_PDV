package poo.ricardo.pdv_ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import poo.ricardo.pdv_ui.menu.MainMenu;
import poo.ricardo.pdv_ui.tabs.LoginPanel;
import poo.ricardo.pdv_ui.tabs.MainPanel;
import poo.ricardo.pdv_ui.tabs.VendaPanel;
import poo.ricardo.pdv_ui.utils.AcessoBanco;
import poo.ricardo.pdv_ui.utils.BancoConnect;
import poo.ricardo.pdv_ui.utils.CallOnCancel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private MainMenu menu = null;
	private JPanel loginSwitcher = null;
	private JPanel mainSwitcher = null;
	private MainPanel mainPanel = null;
	private CardLayout loginSwitcherLayout = null;
	private CardLayout mainSwitcherLayout = null;
	private LoginPanel loginscreen = null;
	private VendaPanel panelvenda = null;
	private AcessoBanco banco = null;

	public MainWindow() {
		super();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		banco=new BancoConnect();
		
		setLayout(new BorderLayout());
		setSize(800,600);
		setTitle("PDV");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new MainMenu(this);
		add(menu,BorderLayout.NORTH);

		panelvenda = new VendaPanel(banco,new CallOnCancel(){
			@Override
			public void cancel() {
				sairVenda();
			}
		});
		
		loginscreen = new LoginPanel(this);
		
		loginSwitcher = new JPanel(new CardLayout());
		loginSwitcherLayout = (CardLayout) loginSwitcher.getLayout();
		
		mainSwitcher = new JPanel(new CardLayout());
		mainSwitcherLayout = (CardLayout) mainSwitcher.getLayout();
		
		add(loginSwitcher,BorderLayout.CENTER);
		
		loginSwitcher.add(loginscreen,"Login");
		loginSwitcher.add(mainSwitcher,"Main");
		
		mainPanel = new MainPanel(this);

		mainSwitcher.add(mainPanel,"MainPanel");
		mainSwitcher.add(panelvenda,"TPane");
		
		setVisible(true);
	}

	public void login() {
		menu.login();
		loginSwitcherLayout.show(loginSwitcher, "Main");
	}
	
	public void logoff() {
		menu.logoff();
		loginSwitcherLayout.show(loginSwitcher, "Login");
	}
	
	public void mainSwitcherShow(String toShow) {
		mainSwitcherLayout.show(mainSwitcher, toShow);
	}
	
	public void novaVenda() {
		mainSwitcherLayout.show(mainSwitcher,"TPane");
		panelvenda.iniciarVenda();
	}
	
	public void sairVenda() {
		mainSwitcherLayout.show(mainSwitcher,"MainPanel");
	}

	public AcessoBanco getBanco() {
		return banco;
	}
	
}
