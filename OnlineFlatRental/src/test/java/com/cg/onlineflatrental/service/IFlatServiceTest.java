package com.cg.onlineflatrental.service;


import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.onlineflatrental.dao.IFlatJpaDao;
import com.cg.onlineflatrental.exception.FlatNotFoundException;
import com.cg.onlineflatrental.exception.InvalidFlatInputException;
import com.cg.onlineflatrental.model.Flat;
import com.cg.onlineflatrental.model.FlatAddress;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class IFlatServiceTest {
	
	//@MockBean
	//IFlatJpaDao iflatjpadao;
	
	@Autowired
	IFlatService iflatservice;
	
	Flat flat=null;
	FlatAddress flatAddress=null;
	
	@Test
	void testAddFlat01() throws FlatNotFoundException , InvalidFlatInputException
	{
		flatAddress=new FlatAddress(10,"Rajajinagar","Bangalore","Karnataka",560086,"India");
		flat=new Flat(78, (float) 20000, flatAddress,"Yes");
		//Mockito.when(iflatjpadao.saveAndFlush(flat)).thenReturn(flat);
        //assertThat(iflatservice.addFlat(flat)).isEqualTo(flat);
		Flat flat1= (iflatservice.addFlat(flat));
		assertNotNull(flat1);
	}
	
	
	
	
	@Test
	void testAddFlat02() throws InvalidFlatInputException {
		flatAddress = new FlatAddress(1, "street", "city", "state", 600001, "country");
		flat = new Flat(1, 3456.0f, flatAddress, "");
		try {
			iflatservice.addFlat(flat);
		} catch (InvalidFlatInputException exception) {
			assertEquals("Availability cannot be empty", exception.getMessage());
		}
	}

	
	@Test
	public void testAddFlat03() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(30,"Kormangala","Tumkur","Kerala",460010,"India");
		Flat flat=new Flat(45, (float) 30000, flatAddress,"Available");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Availability can be only [YES | NO | Yes | No | yes | no | Y | N | y | n]", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat04() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(40,"Jalahalli","Tumkur","Tamil Nadu",360010,"India");
		Flat flat=new Flat(16, (float) 0, flatAddress,"No");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Cost cannot be empty or 0 or negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat05() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(5,"Jalahalli","Mangalore","Delhi",260010,"India");
		Flat flat=new Flat(-150, (float) 6050, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("House Number cannot be Empty or 0 or Negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat06() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(60,"","Bangalore","Karnataka",560780,"India");
		Flat flat=new Flat(150, (float) 6050, flatAddress,"No");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat07() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(70,"Vidyaran@ya Pura","Gokarna","Maharashtra",720780,"India");
		Flat flat=new Flat(50, (float) 22890, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Street cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat08() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(8,"Vidyaranya Pura","","Karnataka",560780,"India");
		Flat flat=new Flat(10, (float) 10050, flatAddress,"y");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat09() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(19,"Kormangala","Bangal1ore","Andra Pradesh",860700,"India");
		Flat flat=new Flat(25, (float) 32000, flatAddress,"Y");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("City cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat10() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(100,"Nandini Layout","Bangalore","",526700,"India");
		Flat flat=new Flat(63, (float) 7500, flatAddress,"n");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat11() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(11,"Nagpura","Bangalore","Karna*taka",560780,"India");
		Flat flat=new Flat(54, (float) 10600, flatAddress,"n");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("State cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat12() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Baswangudi","Mysore","Karnataka",560780,"");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot be Empty", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat13() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(3,"Baswangudi","Mysore","Uttar Pradesh",460780,"Ind9ia");
		Flat flat=new Flat(89, (float) 2800, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("Country cannot contain Numbers or Special Characters", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat14() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",-960780,"India");
		Flat flat=new Flat(45, (float) 9600, flatAddress,"yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode cannot be negative", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat15() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(32,"Magadi","Gokarna","Goa",60780,"India");
		Flat flat=new Flat(32, (float) 29600, flatAddress,"Yes");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode should be of length 6", exception.getMessage());
		}
	}
	
	@Test
	public void testAddFlat16() throws InvalidFlatInputException
	{
		FlatAddress flatAddress=new FlatAddress(3,"Baswangudi","Sirsi","Kashmir",56-780,"India");
		Flat flat=new Flat(15, (float) 2600, flatAddress,"no");
		try
		{
		iflatservice.addFlat(flat);
		}
		catch(InvalidFlatInputException exception)
		{
			assertEquals("PinCode cannot contain Alphabets or Special Characters", exception.getMessage());
		}
	}
	
}


