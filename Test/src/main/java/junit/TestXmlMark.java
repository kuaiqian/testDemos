package junit;

import bgw.utils.XmlUtil;

public class TestXmlMark {
    /**
     * @param args
     */
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Tran><Header><txcode>AL0001</txcode><txseq>61507574731907745693</txseq><tminf>0@172.21.151.56</tminf><txdate>20161216</txdate><txtime>150757</txtime><txsign><![CDATA[SQvOY7OKqQumoAxLt/ULAojdPLB/RT5T4YTYl3AfSVyah8X16qzSh99/QnZhznml1F8kCZ3Ogev7ZbKCA7h4wXE3h5JlSMl89lY10EEFzhUD/as/gsoU6Lm1yREx0LRWZbWQ1cWIwF2ioBcBZpJo5hZHdziZWgEYnoQaC3kEvNk=]]></txsign></Header><Body><request><tx_flag>0</tx_flag><shop_no>105290073991547</shop_no><cunt_no>318218531</cunt_no><cust_nm><![CDATA[王爱东]]></cust_nm><cert_typ>A</cert_typ><cert_id>640321197206060834</cert_id><acct_no>6227004479380174081</acct_no><mobile>13739521598</mobile><acct_flag>0</acct_flag></request></Body></Tran>";
        XmlUtil xmlUtil = new XmlUtil(xml);
    }
}
