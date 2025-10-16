import java.util.Scanner;
import java.text.DecimalFormat;

public class BookingSystem
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		DecimalFormat formatter = new DecimalFormat("R ####.00");
		
		//Constant 
		final int TOTAL_TICKETS = 1050; 
		final int VIP_TICKETS = 200;
		final int REGULAR_TICKETS = 850;
		final double VIP_PRICE = 250;
		final double REGULAR_PRICE = 150;
		
		//variables 
		int remainingVIP = VIP_TICKETS;
		int remainingRegular = REGULAR_TICKETS;
		int remainingTotal = TOTAL_TICKETS;
		char mainOption;
		
		//Event information
		   System.out.println("************************************************************");
		    System.out.println("Welcome to our booking System to book for the Gospel Show");
		    System.out.println("Venue: Gold Reef City");
		    System.out.println("Day: 16 November");
		    System.out.println("Time: 09:00 - 18:00");
		    System.out.println("************************************************************");
		do{
			
			//Main menu with options
			System.out.println("\n1. View Ticket Information.");
			System.out.println("2. Book Tickets");
			System.out.println("3. View remaining Tickets");
			System.out.println("4. Exit");
			System.out.println("***************************************************************");
			System.out.println("Enter your choice: ");
			int choice = keyboard.nextInt();
			
			switch(choice)
			{
				case 1: 
				//Displaying Ticket information 
				   System.out.println("\n Ticket information:");
				   System.out.println("VIP: " + formatter.format(VIP_PRICE) + " per seat (A-D)");
				   System.out.println("Regualr: " + formatter.format(REGULAR_PRICE) + " per seat (Rows E-ZZZ)");
				   System.out.println("Each row has 40 seats");
				   break;
				   
				   
				 case 2: 
				 //Booking tickets
				  System.out.print("Do you want to book a seat(or seats)?(Y or N): ");
		          char cOption = keyboard.next().charAt(0);
				  
				  if(Character.toUpperCase(cOption) == 'N') // if the users says no the program will stop else it will move on
		          {
			         System.out.println("Thank you and have a nice day ");
					 break;
		          }
				  
				  //if the user says yes 
                 boolean bookingActive = true;
				 while(bookingActive)
				 {
					 System.out.print("Please enter the type of ticket(VIP, Regular) or c for cancel: ");
			         String ticketType = keyboard.next().toLowerCase();
					 
					 if(ticketType.equals("c")) //if the user says presses cancel
					 {
						 System.out.println("Booking cancelled. Returning to main menu....");
						 break;
					 }
					 
					 //Error message if the user does not type any of the options 
					 if(!ticketType.equals("vip") && !ticketType.equals("regular"))
					 {
						 System.out.println("Invalid ticket type. Returning to main menu....");
						 continue;
					 }
					 
					 int maxAvailable = ticketType.equals("vip") ? remainingVIP : remainingRegular;
                        if (maxAvailable <= 0) 
						{
                            System.out.println("Sorry, no " + ticketType.toUpperCase() + " tickets left.");
                            break;
                        }
						
						System.out.println("You can buy a maximum of 5 tickets.");
						System.out.print("Enter number of tickets (or 0 to go back): ");
						int numOfTickets = keyboard.nextInt();
						
						if(numOfTickets == 0)
						{
							System.out.println("Returning to ticket type selection...");
							continue;
						}
						
						if(numOfTickets > 5)
						{
							System.out.println("You can only buy up to 5 tickets.");
							continue;
						}
						
						if(numOfTickets > maxAvailable)
						{
							
							System.out.println("Only " + maxAvailable + " " + ticketType.toUpperCase() + " tickets remaining.");
							continue;
						}
						
						
						double totalPrice;
						if(ticketType.equals("vip"))
						{
							totalPrice = numOfTickets * VIP_PRICE;
						}
						else
						{
							totalPrice = numOfTickets * REGULAR_PRICE;
						}
						
						//Confirm purchase 
						System.out.println("You selected " + numOfTickets + " "+ ticketType.toUpperCase() + " tickets");
						System.out.print("Confirm booking? (Y to confirm / C to cancel): ");
                        char confirm = keyboard.next().charAt(0);
                        keyboard.nextLine();
						
						if(Character.toUpperCase(confirm) == 'C')
						{
							System.out.println("Booking cancelled. Returning to main menu...");
							break;
						}
						else if(Character.toUpperCase(confirm) != 'Y')
						{
							System.out.println("Invalid choice. Returning to main menu....");
							break;
						}
						
						//Phone numbers
						String phoneNumber;
						do
						{
							System.out.print("Please enter your cellphone number (or type CANCEL): ");
						    phoneNumber = keyboard.next();
							
							if(phoneNumber.equalsIgnoreCase("cancel"))
							{
								System.out.println("Booking cancelled. Returning to main menu...");
								bookingActive = false;
								break;
							}
							 if(!phoneNumber.matches("^[0-9]{10}$"))
							  {
								  System.out.println("Ïnvalid phone number, please type again. ");
							  }
						
							  
						}while(!phoneNumber.matches("^[0-9]{10}$"));
						
						
						if(!bookingActive) break;
							  
							  //Summary of confimation
							  System.out.println("\nBooking Successfull");
							  System.out.println("Phone: " + phoneNumber);
							  System.out.println("Tickets booked: " + numOfTickets);
							  System.out.println("Total: "+ formatter.format(totalPrice));
							  
							  if(ticketType.equals("vip"))
							  {
								  remainingVIP -= numOfTickets;
							  }
							  else
							  {
								  remainingRegular -= numOfTickets;
							  }
							  remainingTotal -= numOfTickets;
							  
							  
							  System.out.println("A confirmation message will be sent to "+ phoneNumber);
							  bookingActive = false;
				 }
				 break;
				 
				 case 3:
				 //Viewing how many tickets are available 
				 System.out.println("\nRemaining Tickets:");
				 System.out.println("VIP: "+ remainingVIP);
				 System.out.println("Regular: " + remainingRegular);
                 System.out.println("Total: " + remainingTotal);
                 break;
				 
				 case 4:
				 System.out.println("\nGoodbye. Thank you for booking, hope to see you there");
				 break;
				 
				 default:
				 System.out.println("Invalid option. Please choose 1–4.");
						
			}
			
			//Ask if the user wants to return to the main menu
			System.out.print("\nReturn to main menu? (Y/N): ");
			 mainOption = keyboard.next().charAt(0);
			 
		}while(Character.toUpperCase(mainOption) == 'Y');
		//Goodbye message after the user id done
		System.out.println("\nGoodbye. Thank you for using our services");
	}		
		
}
	
