    public class LinkStrand implements IDnaStrand {
        private String myInfo;
        private Node myFirst, myLast;
        private long mySize;
        private int myAppends;
        private Node myCurrent;
        private int myIndex;
        private int myLocalIndex;


        private class Node {
            String info;
            Node next;

            public Node(String s) {
                info = s;
                next = null;
            }
        }

        public LinkStrand() {
            this("");
        } //first default constructor, no parameters


        public LinkStrand(String s) {
            initialize(s);
        } //second constructor, needs string parameter

        @Override
        public long size() {

            return mySize;

        }

        @Override
        
        /*method initializes every previously defined 
        private variable for use throughout the class whenever
        this method is called within another*/
        
        //String source is the current dna strand being used by the class
        public void initialize(String source) {
            myFirst = new Node(source);
            myLast = myFirst;
            mySize = source.length();
            myAppends = 0;
            myInfo = source;
            myCurrent = myFirst;
            myIndex = 0;
            myLocalIndex = 0;
        }

        @Override
        public IDnaStrand getInstance(String source) {
            return new LinkStrand(source);
        }

        @Override
        /*method adds one new node to the end of the 
        internal linked list and updates the state the maintain the invariant*/
        
        //String dna is the current dna strand being appended by the method
        public IDnaStrand append(String dna) {
            myLast.next = new Node(dna);
            myLast = myLast.next;
            myAppends++;
            mySize += dna.length();
            return this;
        }

        @Override
        /*
        method creates a new LinkStrand object that is the reverse
        of the object on which it's called
        */
        public IDnaStrand reverse() {
            Node list = myFirst;
            Boolean run1 = true;
            LinkStrand changer = new LinkStrand("");
            while (list != null) {
                StringBuilder x = new StringBuilder(list.info);
                String y = x.reverse().toString();
                if (run1 == true) {
                    changer.initialize(y);
                    run1 = false;
                }
                else {
                    changer.append(y);
                }
                list = list.next;
            }
            Node current = changer.myFirst;
            Node prev = null;
            Node next = null;
            while (current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            //returns a new strand– does not alter the strand on which it's called
            changer.myFirst = prev;
            changer.myLast = current;
            return changer;
        }


        @Override
        /*this method returns the String representation 
        of the entire DNA strand*/
        public String toString() {
            Node list = myFirst;
            StringBuilder linker = new StringBuilder();
            while (list != null) {
                linker.append(list.info);
                list = list.next;
            }
            return linker.toString();
        }

        @Override
        public int getAppendCount() {
            return myAppends;
        }

        @Override
        
        /*this method finds a character at a specific index in a 
        linked list of strings*/
        
        public char charAt(int index) {
            //int count = 0; // keep track of node
            //int dex = 0; //keep track of which character
            if (index < 0 || index > mySize - 1) {
                throw new IndexOutOfBoundsException();
            }
            if (index < myIndex) {
                myLocalIndex = 0;
                myIndex = 0;
                myCurrent = myFirst;
            }
            while (myIndex != index) {
                if (myIndex != index) {
                    myIndex++;
                    myLocalIndex++;
                }
                if (myLocalIndex >= myCurrent.info.length()) {
                    myLocalIndex = 0;
                    if (myCurrent.next != null) {
                        myCurrent = myCurrent.next;
                    }
                }
            }
            return myCurrent.info.charAt(myLocalIndex);
        }
    }


