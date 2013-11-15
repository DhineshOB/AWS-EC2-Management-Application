/**
 * 
 */
/**
 * @author Dhinesh
   @Version 1.3
 *
 */
package ec2lab;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.model.*;

import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

import java.util.*;


public class EC2LabMain 
{

	
	public static void main(String[] args) throws  NoClassDefFoundError,NumberFormatException,com.amazonaws.AmazonServiceException
 	{
		
		System.out.println("\nEnter Access Key:");
		Scanner key = new Scanner(System.in);
		String acc_key = key.nextLine();
		System.out.println("Enter Secret Key:");
		String sec_key = key.nextLine();
		AmazonEC2Client EC2 = new AmazonEC2Client(new BasicAWSCredentials(acc_key,sec_key));
		EC2.setEndpoint("ec2.us-west-2.amazonaws.com");
		
		System.out.println("\n===============================");
		System.out.println("AWS EC2 Management Application");
		System.out.println("===============================");
		int select;		
		try
		{
		try {
			do {
				DescribeInstancesResult Res = EC2.describeInstances();
				List<Reservation> L = Res.getReservations();
				DescribeImagesRequest request = new DescribeImagesRequest().withOwners("self");
				Collection<Image> images = EC2.describeImages(request).getImages();
				String option;
				Scanner opt = new Scanner(System.in);
				System.out.println("1) List existing instances\n"
						+ "2) Create an instance\n" + "3) Start an Instance\n"
						+ "4) Stop an Instance\n" + "5) Delete an Instance\n"
						+ "6) Reboot an Instance\n"
						+ "7) List Existing Private AMI's\n"
						+ "8) Create an AMI from Existing Instance\n"
						+ "9) Launch Instance from AMI\n" + "10) Exit\n\n"
						+ "Enter option between 1 to 10:");

				option = opt.nextLine();
				select = Integer.parseInt(option);

				switch (select)

				{
				case 1:

					//List Instances

					int flag = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("HyperVisor Type:"
									+ I.getHypervisor());
							System.out.println("Instance Type:"
									+ I.getInstanceType());
							System.out.println("Instance State:" + I.getState().getName());
							System.out.println("Public IP Address:" + I.getPublicIpAddress());
							System.out.println("Availability Zone:" + I.getPlacement().getAvailabilityZone()
									+ "\n");
							
							flag++;
						}
					}
					break;

				case 2://create an instance

					System.out
							.println("Creating a New Instance - Amazon Linux AMI 2013.09(64-bit)...........\n");
					RunInstancesRequest runRequest = new RunInstancesRequest()
							.withImageId("ami-d03ea1e0").withMinCount(1)
							.withMaxCount(1).withInstanceType("t1.micro");
					EC2.runInstances(runRequest);
					break;
		
				case 3://start an Instance
					int flag_start = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag_start + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("Instance State:" + I.getState().getName()
									+ "\n");
							flag_start++;
						}
					}
					System.out.println("Enter the Instance ID to Start:");
					String start_ins = opt.nextLine();
					System.out.println("Starting Instance " + start_ins
							+ ".......\n");
					StartInstancesRequest startRequest = new StartInstancesRequest()
							.withInstanceIds(start_ins);
					EC2.startInstances(startRequest);
					break;

				case 4://Stop an Instance
					int flag_stop = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag_stop + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("Instance State:" + I.getState().getName()
									+ "\n");
							flag_stop++;
						}
					}
					System.out.println("Enter the Instance ID to Stop:");
					String stop_ins = opt.nextLine();
					System.out.println("Stopping Instance " + stop_ins
							+ ".......\n");
					StopInstancesRequest stopRequest = new StopInstancesRequest()
							.withInstanceIds(stop_ins);
					EC2.stopInstances(stopRequest);
					break;

				case 5://Delete an Instance
					int flag_del = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag_del + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("Instance State:" + I.getState().getName()
									+ "\n");
							flag_del++;
						}
					}
					System.out.println("Enter the Instance ID to Delete:");
					String del_ins = opt.nextLine();
					System.out.println("Deleting Instance " + del_ins
							+ ".......\n");
					TerminateInstancesRequest ter = new TerminateInstancesRequest()
							.withInstanceIds(del_ins);
					EC2.terminateInstances(ter);
					break;
				case 6://Reboot an Instance
					int flag_reb = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag_reb + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("Instance State:" + I.getState().getName()
									+ "\n");
							flag_reb++;
						}
					}
					System.out.println("Enter the Instance ID to Reboot:");
					String reb_ins = opt.nextLine();
					System.out.println("Rebooting Instance " + reb_ins
							+ ".......\n");

					RebootInstancesRequest reb = new RebootInstancesRequest()
							.withInstanceIds(reb_ins);
					EC2.rebootInstances(reb);
					break;
				case 7:
					int flag_ami = 1;

					for (Image Im : images) {
						System.out.println(flag_ami + ")");
						System.out.println("Image ID:" + Im.getImageId());
						System.out.println("Owner ID:" + Im.getOwnerId());
						System.out
								.println("AMI Status:" + Im.getState() + "\n");
						flag_ami++;
					}
					break;
				case 8:
					int flag_cami = 1;
					for (Reservation R : L) {
						List<Instance> Li = R.getInstances();
						for (Instance I : Li) {
							System.out.println(flag_cami + ")");
							System.out.println("Instance ID:"
									+ I.getInstanceId());
							//System.out.println(L);
							System.out.println("Instance State:" + I.getState().getName()
									+ "\n");
							flag_cami++;
						}
					}
					System.out.println("Enter Instance ID to create an AMI:");
					String ami_ins = opt.nextLine();
					System.out.println("Enter name for AMI:");
					String ami_name = opt.nextLine();
					System.out.println("Creating " + ami_name
							+ " AMI from instance " + ami_ins + ".......\n");

					CreateImageRequest create = new CreateImageRequest()
							.withInstanceId(ami_ins).withName(ami_name);
					EC2.createImage(create);
					break;
				case 9:

					int flag_ami_c = 1;

					for (Image Im : images) {
						System.out.println(flag_ami_c + ")");
						System.out.println("Image ID:" + Im.getImageId());
						System.out
								.println("AMI Status:" + Im.getState() + "\n");
					}
					System.out.println("Enter the AMI Image ID to Launch:");
					String ami_cre = opt.nextLine();
					System.out.println("Creating Instance from AMI -" + ami_cre
							+ ".......\n");
					RunInstancesRequest runimg = new RunInstancesRequest()
							.withImageId(ami_cre).withMinCount(1)
							.withMaxCount(1);
					EC2.runInstances(runimg);

					break;
			
				case 10:
					System.out.println("The program will now exit!\n"
							+ "Have a good day!");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid Entry!Please enter number between 1 to 10\n");
					break;
				}

			} while (select <= 999999999);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("Enter Only Numerical Values! Restart the Application");
		}

		}catch(com.amazonaws.AmazonServiceException a)
		{
			// TODO: handle exception
			System.out.println("Not a valid input! Restart the Application");
		}
	
	}
	
}