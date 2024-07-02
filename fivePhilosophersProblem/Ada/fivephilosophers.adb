with Ada.Text_IO; use Ada.Text_IO;
with Ada.Command_Line;

procedure fivephilosophers is

	task type Semaphore is 
	 entry Lock;
	 entry Unlock;
	end Semaphore;
	
	task body Semaphore is
	 begin
	  loop
	   select
	    accept Lock;
	    accept Unlock;
	   or
	   terminate;
	  end select;
	 end loop;
	end Semaphore;
		
	numberOfPhilosophers : Integer := Integer'Value(Ada.Command_Line.Argument(1));
	numberOfMeals : Integer := Integer'Value(Ada.Command_Line.Argument(2));
	
	C : array(0 .. numberOfPhilosophers - 1) of Integer := (others => 0);
	
	mutex : Semaphore; 
	prisem : array(0 .. numberOfPhilosophers - 1) of Semaphore;
	 	
	procedure test (i : in Integer);
	
	procedure test (i : in Integer) is
	begin	
	 if C((i-1) mod numberOfPhilosophers) /= 2 and  C(i mod numberOfPhilosophers) = 1 and C((i+1) mod numberOfPhilosophers) /= 2 then
	  C(i mod numberOfPhilosophers) := 2;
	  prisem(i mod numberOfPhilosophers).Lock;
	 end if;
	end test;


	task type Philosopher is
	 entry Start(k : in Integer);
	end Philosopher;

	task body Philosopher is
	 i : Integer;
	 counter : Integer := 1;
	 begin
	  accept Start(k : in Integer) do
	   i := k;
	  end Start;
	 while counter <= numberOfMeals loop
	  mutex.Lock;
	   C(i) := 1;
	   test(i);
	  mutex.Unlock;
	  
	  prisem(i).Unlock;
	  
	  mutex.Lock;
	   Put_Line("Philosopher number" & Integer'Image(i) & " has eaten his" & Integer'Image(counter) & " meal"); 
	   C(i) := 0;
	   test(i - 1 mod numberOfPhilosophers);
	   test(i + 1 mod numberOfPhilosophers);
	  mutex.Unlock;
	  counter := counter + 1;
	 end loop;
	end Philosopher;
	
	Philo : array(0 .. numberOfPhilosophers - 1) of Philosopher;
	
	begin
	 null;
	 for k in 0 .. numberOfPhilosophers - 1 loop
	  Philo(k).Start(k);
	 end loop;
end fivephilosophers;

