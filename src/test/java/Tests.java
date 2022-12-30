import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Tests class for this task(Ex_1) implementing Observer DP.
 * this is tests for the case: while unregistering member we set his usb to be constantly the last one.
 * status: working well.
 */
public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){

        GroupAdmin admin = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        admin.register(m1);
        admin.register(m2);
        String s1 = "Aaron";
        String s2 = "Itamar";
        admin.insert(0,s1);
        admin.append(" and " + s2 + " did this OOP task ! :)");

        logger.info(()->JvmUtilities.objectFootprint(admin));
        logger.info(()->JvmUtilities.objectFootprint(m1,m2));
        logger.info(()->JvmUtilities.objectTotalSize(m1));
        logger.info(()->JvmUtilities.objectTotalSize(m2));
        logger.info(()->JvmUtilities.objectTotalSize(admin));
        logger.info(JvmUtilities::jvmInfo);
    }
    @Test
    public void ConcreteMember_test()
    {

        UndoableStringBuilder usb = new UndoableStringBuilder();
        ConcreteMember cm = new ConcreteMember();
        for (int k = 0 ; k< 50 ; k++)
        {
            usb.append("Argentina Wins the 2022 World Cup");
            cm.update(usb);
        }
        logger.info(()->JvmUtilities.objectFootprint(cm , usb));
        logger.info(()->JvmUtilities.objectTotalSize(cm));

        for (int i = 0 ; i< 50 ; i++)
        {
            usb.undo();
            cm.update(usb);
        }

        logger.info(()->JvmUtilities.objectFootprint(cm));
        logger.info(()->JvmUtilities.objectTotalSize(cm));
        logger.info(JvmUtilities::jvmInfo);
        // Looking for the best running time more than memory efficiency

    }
    @Test
    public void UndoableStringBuilder_test()
    {

        UndoableStringBuilder str = new UndoableStringBuilder();
        for (int i = 0 ; i< 20 ; i++)
        {
            str.append("Messi Ronaldo");
        }

        logger.info(()->JvmUtilities.objectFootprint(str));
        logger.info(()->JvmUtilities.objectTotalSize(str));

        for (int k = 0 ; k< 20 ; k++)
        {
            str.undo();
        }

        logger.info(()->JvmUtilities.objectFootprint(str));
        logger.info(()->JvmUtilities.objectTotalSize(str));

        logger.info(JvmUtilities::jvmInfo);

        // remember: we want more good runtime than memory
    }
    @Test
    public void GroupAdmin_test()
    {

        GroupAdmin admin = new GroupAdmin();
        ArrayList<Member> memberList = new ArrayList<>();
        for (int i = 0 ; i< 10 ; i++)
        {
            ConcreteMember member = new ConcreteMember();
            admin.register(member);
            memberList.add(member);
        }
        admin.append("Hi dear members");

        logger.info(()->JvmUtilities.objectFootprint(admin));
        logger.info(()->JvmUtilities.objectTotalSize(admin));

        for (int i = 0 ; i< 10 ; i++)
        {

            admin.unregister(memberList.get(i));
        }

        logger.info(()->JvmUtilities.objectFootprint(admin));
        logger.info(()->JvmUtilities.objectTotalSize(admin));

        logger.info(JvmUtilities::jvmInfo);

        // remember: we want more good runtime than memory
    }


// ConcreteMembers test
    @Test
    void update()
    {
        ConcreteMember m1 = new ConcreteMember();
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("Hi World!");
        m1.update(usb);
        assertEquals("Hi World!",m1.toString());
    }
    @Test
    void tostring()
    {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember();
        admin.register(m1);
        admin.append("Hi everyone!");
        assertEquals("Hi everyone!",m1.toString());
    }

    //GroupAdmin tests
    @Test
    void register()
    {
        GroupAdmin admin = new GroupAdmin();
        ArrayList<Member> members = new ArrayList<>();

        int number_of_members = 0;
        for ( int i =0 ; i < 5; i ++)
        {
            ConcreteMember newMember = new ConcreteMember();
            admin.register(newMember);
            members.add(newMember);
            number_of_members ++ ;
        }
        assertEquals(number_of_members , members.size());

        /* Assert between two registered objects after to append and update everyone */

        GroupAdmin admin1  = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember();
        ConcreteMember member2 = new ConcreteMember();
        admin1.register(member1);
        admin1.register(member2);
        admin1.append("Hi!");
        assertEquals(member1.toString(), member2.toString());
    }

    @Test
    void unregister()
    {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember Cmember = new ConcreteMember();

        admin.register(Cmember);
        admin.append("OOP is the best course");

        admin.unregister(Cmember);
        admin.append("!");
        assertNotEquals("OOP is the best course!",Cmember.toString());
    }

    @Test
    void insert()
    {
        GroupAdmin leader = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();

        leader.register(m1);
        leader.register(m2);

        leader.insert(0,"My name is ");
        assertEquals("My name is ",m1.toString());
        assertEquals("My name is ",m2.toString());


        String check = "Aaron";
        leader.unregister(m2);
        leader.insert(11,check);

        assertEquals("My name is Aaron",m1.toString());
        assertNotEquals("My name is Aaron",m2.toString());
    }

    @Test
    void append()
    {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();

        admin.register(m1);
        admin.register(m2);

        admin.append("My name is");
        assertEquals("My name is",m1.toString());
        assertEquals("My name is",m2.toString());

        admin.unregister(m2);
        String check = " Itamar";
        admin.append(check);

        assertEquals("My name is Itamar",m1.toString());
//        assertNotEquals("My name is Itamar",m2.toString());

    }

    @Test
    void delete()
    {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();

        admin.register(m1);
        admin.register(m2);

        admin.append("Hello!");
        assertEquals("Hello!",m1.toString());
        assertEquals("Hello!",m2.toString());

        admin.delete(0,5);
        assertEquals("!",m1.toString());
        assertEquals("!",m2.toString());


    }

    @Test
    void undo()
    {

        GroupAdmin admin= new GroupAdmin();
        ConcreteMember member = new ConcreteMember();
        admin.register(member);
        admin.append("Aaron") ;
        admin.append("Itamar");
        admin.undo();
        assertEquals("Aaron", member.toString());
    }







}