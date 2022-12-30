//imports:
import java.util.ArrayList;
/**
 * This is the second task (Ex_1) in Object-Oriented Programming course (OOP).
 * In this task, we added to our UndoableStringBuilder class an option to
 * organize a group of update recipients to send users all the updates in real time.
 * @authors Aaron Luchan & Itamar Kuznitsov.
 * @version 0.1 Dec 2022
 */


/**
 * This is the GroupAdmin class (observable)
 */
public class GroupAdmin implements Sender{
    //list of all the members that has been registered to the system
    private ArrayList<ConcreteMember> members;
    // UndoableStringBuilder object
    private UndoableStringBuilder usb;

    /** Constructor:
      */
    public GroupAdmin() {
        this.members = new ArrayList<ConcreteMember>();
        this.usb = new UndoableStringBuilder();
    }


    /**
     * function that register the member to the group.
     * @param obj
     */
    @Override
    public void register(Member obj) {
        // adding the member to the list of all members
        this.members.add((ConcreteMember) obj);
    }

    /**
     * function that unregister member from the group.
     * @param obj
     */
    @Override
    public void unregister(Member obj) {
    /** Option: you can add those two lines to the code to keep
     *  the unregistered observer(member) in the last registered appearance
     *  (make it much more Observer DP but wasn't requested...).
     *  otherwise the unregistered will keep update.
     */
        ConcreteMember c = (ConcreteMember) obj;
        c.update(new UndoableStringBuilder().append(this.usb.toString()));

        // removing the member form the register list
        this.members.remove((ConcreteMember) obj);
    }


    /**
     * implementing the UndoableStringBuilder functions and calling updateMembers func:
     */
    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        updateMembers(this.usb);
    }

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        updateMembers(this.usb);
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        updateMembers(this.usb);
    }

    @Override
    public void undo() {
        this.usb.undo();
        updateMembers(this.usb);
    }

    /**
     * assistance function that update all members that in the group - ArrayList(members).
     * @param usb
     */
    public void updateMembers(UndoableStringBuilder usb){
        // for each register make an update
        for (ConcreteMember member: members) {
            member.update(usb);
        }
    }
}