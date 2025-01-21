package strings;

public class IPAddress
{
    public String validIPAddress(String queryIP)
    {
        queryIP = queryIP.toLowerCase();
        if(queryIP.indexOf('.') > 0)
        {
            if(checkValidIPv4(queryIP))
                return "IPv4";
            else
                return "Neither";
        }

        else if(queryIP.indexOf(':') > 0)
        {
            if(checkValidIPv6(queryIP))
                return "IPv6";
            else
                return "Neither";
        }
        else
            return "Neither";

    }

    private boolean checkValidIPv4(String ipv4)
    {
        if(ipv4.charAt(0) == '.' || ipv4.charAt(ipv4.length()-1) == '.')
            return false;

        String[] arr = ipv4.split("\\.");

        if(arr.length != 4)
            return false;

        for(String address: arr)
        {
            if(address.length() > 3 || address.isEmpty() || checkLeadingZero(address))
                return false;

            int num = getNum(address);
            if(num < 0 || num > 255)
                return false;
        }
        return true;
    }

    private boolean checkLeadingZero(String address)
    {
        if(address.length() > 1 && address.charAt(0) == '0')
            return true;

        return false;
    }

    private int getNum(String n)
    {
        int num = 0;
        for(int i = 0; i < n.length(); i++)
        {
            char ch = n.charAt(i);
            if(ch < '0' || ch > '9')
                return -1;
            num = num * 10 + (ch - '0');
        }
        return num;
    }

    private boolean checkValidIPv6(String ipv6)
    {
        if(ipv6.charAt(0) == ':' || ipv6.charAt(ipv6.length()-1) == ':')
            return false;

        String[] arr = ipv6.split(":");

        if(arr.length != 8)
            return false;

        for(String address: arr)
        {
            if(address.length() > 4 || address.isEmpty() || !checkHex(address))
                return false;

        }
        return true;
    }

    private boolean checkHex(String n)
    {
        for(int i = 0; i < n.length(); i++)
        {
            char ch = n.charAt(i);
            if((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f'))
                return false;
        }
        return true;
    }
}
