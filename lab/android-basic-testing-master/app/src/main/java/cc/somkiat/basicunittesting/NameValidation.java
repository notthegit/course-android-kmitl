package cc.somkiat.basicunittesting;

class NameValidation {
    String [] nameError = new String[]{"Empty", "Null"};


    public int validate(String name) {
        //return isEmpty(name)&& isNull(name);
        return 1;
    }
    public boolean isEmpty(String username) {
        if (username.isEmpty()){
            return false;
        }
        return true;
    }
    public boolean okName(String username) {
        if (username.length() > 20 || (username.length() < 2))
        {
            return false;
        }
        return true;
    }
}
