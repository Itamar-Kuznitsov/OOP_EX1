/**
 * This is the ConcreteMember class (observer).
 * @authors Aaron Luchan & Itamar Kuznitsov.
 * @version 0.1 Dec 2022
 */
public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;

    /** Constructor:
      */
    public ConcreteMember (){
        this.usb = null;
    }

    /** As requested this is update function using shallow copy.
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /** toString function
     *  @return this.usb
     */
    public String toString() {
        return this.usb.toString();
    }
}

