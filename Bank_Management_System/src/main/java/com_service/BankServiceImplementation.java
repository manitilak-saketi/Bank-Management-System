package com_service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.DAO.BankUserDAO;
import com.DAO.BankUserDAOImp;
import com.DAO.BankUserException;
import com.model.BankStatement;
import com.model.BankUserDetails;
public class BankServiceImplementation implements BankService {
	BankUserDetails bud;
	BankStatement bs =new BankStatement();
	BankStatementService bss=new BankStatementServiceImp();
	Scanner s=new Scanner(System.in);
	BankUserDAO b=new BankUserDAOImp();
	@Override
	public void forSleep(String value) {
		for(int i=0;i<value.length();i++) {
			try {
				System.out.print(value.charAt(i));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public void userRegistration() {
		BankUserDetails bud= new BankUserDetails();
		List<BankUserDetails>l=b.getAllBankUserDetails();
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter your name");
				String name=s.nextLine();
				int c=0;
				for(int i=0;i<name.length();i++) {
					if(!Character.isAlphabetic(name.charAt(i))&& name.charAt(i)!=' '&& name.charAt(i)!='.')
						c++;
				}
				if(c==0) {
					bud.setName(name);
					status=false;
				}
				else {
					throw new BankUserException("Invalid name");
				}
			} 
			catch (BankUserException bu) {
				System.out.println(bu.getMsg());
			}
		}
		boolean email_status=true;
		while(email_status) {
			try {
				System.out.println("Enter your email Id");
				String email=s.next().toLowerCase();
				int email_count=10;
				String s1[]=email.split("@");
				if(s1.length==2) {
					if(s1[1].equals("gmail.com")) {
					for(int i=0;i<s1[0].length();i++) {
						if((Character.isAlphabetic(s1[0].charAt(i))|| Character.isDigit(s1[0].charAt(i)))) {
							email_count++;	
						}
					}
				}
					else {
						throw new BankUserException("Invalid user email id");
					}
							if(email_count==email.length()) {
								long em=l.stream().filter(user->user.getEmail().equals(email)).count();
								if(em==0) {
									bud.setEmail(email);
									email_status=false;
								}
							else
								throw new BankUserException("Already email exists");
						}
				}
				else {
					throw new BankUserException("Invalid user email");
				}
			} catch (BankUserException be) {
				System.out.println( be.getMsg());
			}
		}
		
		boolean aadhar_status=true;
		while(aadhar_status) {
		try {
			System.out.println("Enter your aadhar No");
			long aadharNo=s.nextLong();
			if(bud.validateAadharNo(aadharNo)) {
				int aadhar_existed=0;
				for(BankUserDetails b1:l) {
				if(b1.getAadharNo()==aadharNo) 
					aadhar_existed++;
				if(aadhar_existed==0) {
					bud.setAadharNo(aadharNo);
					aadhar_status=false;
				}
				else {
					throw new BankUserException("AadharNo already exists");

				}
			}
			}
			else {
				throw new BankUserException("invalid aadharNo");
			}
		}	
		catch(BankUserException e) {
			System.out.println(e.getMsg());
		}
		catch(InputMismatchException i) {
			System.out.println("enter digits");
			s.next();
			
		}
		}
		
		boolean mobile_status=true;
		while(mobile_status) {
			try {
				System.out.println("Enter your mobile No");
				long mobileNo=s.nextLong();
				if(mobileNo>=6000000000l && mobileNo<=9999999999l) {
					long mobile_existed=l.stream().filter(user->user.getMobileNo()==mobileNo).count();
					if(mobile_existed==0) {
						bud.setMobileNo(mobileNo);
						mobile_status=false;
					}
					else {
						throw new BankUserException(" mobileNo already exists");
					}
				}
				else {
					throw new BankUserException("invalid mobileNo");
				}
			} 
		
		catch (BankUserException bb) {
			System.out.println(bb.getMsg());
		}
		catch(InputMismatchException i) {
			System.out.println("invalid input");
			s.next();
		}
		}
		
		boolean pan_status=true;
		boolean lastChar=false;
		while(pan_status) {
			try {
				System.out.println("Enter pan");
				String panNo=s.next().toUpperCase();
				char []ch=panNo.toCharArray();
				if(ch.length==10) {
					for(int i=0;i<=(ch.length/2)-1;i++) {
						if(!Character.isAlphabetic(ch[i])) {
							throw new BankUserException("Invalid panId it's should be starts with alphabets");
						}
					}
					for(int i=ch.length/2;i<ch.length-1;i++) {
						if(!Character.isDigit(ch[i])) {
							throw new BankUserException("Invalid panId it's should be continue with digits after alphabets");
						}
					}
					if(!Character.isAlphabetic(ch[ch.length-1])) {
						lastChar=true;
						throw new BankUserException("invalid panId format last character must ended with alphabet");
					}			
					long pc=l.stream().filter(user->user.getPanNo().equals(panNo)).count();
					if(pc==0) {
						bud.setPanNo(panNo);
						pan_status=false;
					
					}
					else {
						throw new BankUserException("panId already exists");
					}
					}
				else {
					throw new BankUserException("PanId should contains exactly 10 characters");
				}
				}
				
			 catch (BankUserException b) {
				System.out.println(b.getMsg());
			}
			catch(InputMismatchException i) {
				System.out.println("invalid input");
				s.next();
			}
		
		}
		System.out.println("Enter your Address");
		String address=s.next();
		bud.setAddress(address);
		System.out.println("Enter your gender");
		String gender=s.next();
		bud.setGender(gender);
		System.out.println("Enter your amount");
		Double amount=s.nextDouble();
		bud.setAmount(amount);
		b.insertBankUserDetails(bud);
	}
	@Override
	public void userLogin() {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter the emailId or AccountNo");
				String emailOrAccountNo=s.next();
				System.out.println("Enter the pin");
				int pin=s.nextInt();
				bud=b.getUserDetailsUsingEmailIdOrAccountNoAndPin(emailOrAccountNo, pin);
				if(bud!=null) {
					status=false;
					System.out.println("Enter \n 1. Credit \n 2. Debit \n 3. Check Balance \n 4. Change Pin \n 5. Check Statement \n 6. Request For Delete Account");
					switch (s.nextInt()) {
					case 1:System.out.println("Credit");
						credit(pin,bud.getAmount());
						break;
					case 2:System.out.println("Debit");
						debit(pin,bud.getAmount());
						break;
					case 3:System.out.println("Check Balance");
						break;
					case 4:System.out.println("Change Pin");
						break;
					case 5:System.out.println("Check Statement");
						break;
					case 6:System.out.println("Remove Account \n Enter accountNo Or AadharNo");
						String accOrAadhar=s.next();
						b.removeAccount(pin, accOrAadhar);
						System.out.println("Account removed successfully");
						break;
					default:System.out.println("Invalid choice");
						break;
					}
				}
				else {
					throw new BankUserException("Enter valid credentials");
				}
			} catch (BankUserException b) {
				System.out.println(b.getMsg());
			}
			catch(InputMismatchException i) {
				System.out.println("Invlid input");
				s.next();
			}
		}	
	}
	@Override
	public void debit(int pin,double bankAmount) {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter your amount");
				double userAmount=s.nextDouble();
				if(userAmount>=0) {
					if(bankAmount>=userAmount) {
						double balanceAmount=bankAmount-userAmount;
						int res=b.updateAmountByUsingPin(pin,balanceAmount);
						if(res>0) {
							System.out.println("Amount debited");
							BankStatement bs=new BankStatement();
							bs.setTransactionAmount(userAmount);
							bs.setBalanceAmount(balanceAmount);
							bs.setDateOftransaction(LocalDate.now());
							bs.setTimeOfTransaction(LocalTime.now());
							bs.setTransactionType("debit");
							bs.setUserId(bud.getId());
							int statementDetails=bss.bankStatementDetails(bs);
							if(statementDetails>0) {
								System.out.println("Amount Debited");
								bankAmount=balanceAmount;
							}
						}
						else {
							System.out.println("Server error 404");
						}
					}
					else {
						throw new BankUserException("Insufficient Amount");
					}
				}
				else {
					throw new BankUserException("Invalid amount");
				}
			} 
			catch (BankUserException b) {
				 System.out.println(b.getMsg());
			}
			System.out.println("do you want to continue");
			System.out.println("yes \n no");
			if(s.next().equalsIgnoreCase("no")) {
				status=false;
				System.out.println("Exit");
			}

		}	
	}
	@Override
	public void credit(int pin, double bankAmount) {
		boolean status=true;
		while(status) {
			try {
				System.out.println("Enter your amount");
				double userCreditAmount=s.nextDouble();
				if(userCreditAmount>0) {
						double balanceAmount=bankAmount+userCreditAmount;
						int res=b.updateAmountByUsingPin(pin,balanceAmount);
						if(res>0) {
							System.out.println("Amount Credited");
							BankStatement bs=new BankStatement();
							bs.setTransactionAmount(userCreditAmount);
							bs.setBalanceAmount(balanceAmount);
							bs.setDateOftransaction(LocalDate.now());
							bs.setTimeOfTransaction(LocalTime.now());
							bs.setTransactionType("Credit");
							bs.setUserId(bud.getId());
							int statementDetails=bss.bankStatementDetails(bs);
							if(statementDetails>0) {
								System.out.println("Amount Credited");
								bankAmount=balanceAmount;
							}
							System.out.println(bs.getBalanceAmount());
							status=false;
						}
						else {
							System.out.println("Server error 404");
						}
					}
				else {
					throw new BankUserException("Invalid amount");
				}
			} 
			catch (BankUserException b) {
				 System.out.println(b.getMsg());
			}
		}
	}
}

