package com.expocms.server.tools;

public final class ToolMoney {
	
	private static final String[] NUMBER_IN_CHINESE   = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
	private static final String[] Y_UNIT_IN_CHINESE   = new String[] { "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾", "佰", "仟" };
	private static final String[] F_UNIT_IN_CHINESE   = new String[] { "角", "分" };
	private static final String   Y_FINISH_IN_CHINESE = "整";
	private static final int GROUP_NUMBER_IN_CHINESE  = 4;
	
	private ToolMoney() {}
	
	public static String F2Y(long money) {
		String s = "" + money;
		
		if(s.length() > 2)
			return s.substring(0, s.length() - 2) + "." + s.substring(s.length() - 2);
		
		if(s.length() == 2)
			return "0." + s;
		
		if(s.length() == 1)
			return "0.0" + s;
		
		return "0.00";
	}
	
	public static String F2ChineseY(long input) {
		String money_str = F2Y(input);
		
		int index = money_str.indexOf('.');
		if(index == -1)
			return money_str;
		
		String Y_part = money_str.substring(0, index);
		if(money_str == null || money_str.equals(""))
			return money_str;
		
		String F_part = money_str.substring(index + 1);
		if(F_part == null || F_part.equals("") || F_part.length() != 2)
			return money_str;
		
		StringBuffer sb = new StringBuffer();
		
		if(Y_part.equals("0")) {
			sb.append(NUMBER_IN_CHINESE[0]);
		} else {
			index = 0;
			int space = 0;
			int group = 0;
			boolean is_group_zero = true;
			
			while(Y_part.equals("") == false) {
				int number;
				try {
					number = Integer.parseInt(Y_part.substring(Y_part.length() - 1));
				} catch (Exception e) {
					return money_str;
				}
				
				if(number != 0)
					is_group_zero = false;
				else
					space += 1;
	
				String number_C = "";
				if(number != 0) {
					/**
					 * number group : xxxx,xxxx,xxxx,xxxx
					 *                 g3   g2   g1   g0
					 */
					int new_group = index / GROUP_NUMBER_IN_CHINESE;
					
					/**
					 * check if there's 0 between two numbers ...
					 */
					if(space >= 1) {
						// clear the space flag ...
						space = 0;
						/**
						 * check if there's more data already been push into buffer ...
						 */
						if(sb.length() >= 2) {
							/**
							 * check if the last pushed data is 'thousand' and if there's two groups split (ex: g0 to g2|g3) ...
							 */
							if((new_group - group) >= 2 || Y_UNIT_IN_CHINESE[3].equals("" + sb.charAt(1)) == false)
								sb.insert(0, NUMBER_IN_CHINESE[0]);
						} else {
							/**
							 * check if the last pushed data is 'thousand' ...
							 */
							if(sb.length() > 0 && NUMBER_IN_CHINESE[0].equals("" + sb.charAt(0)) == false)
								sb.insert(0, NUMBER_IN_CHINESE[0]);
						}
					}
					
					/**
					 * translate number to Chinese ...
					 */
					number_C = NUMBER_IN_CHINESE[number];
					/**
					 * check if there's 0 happened inside ...
					 */
					if(is_group_zero == false) {
						is_group_zero = true;
						number_C += Y_UNIT_IN_CHINESE[index];
					}
					
					/**
					 * check if there's new group arrived ...
					 */
					if(group != new_group) {
						group = new_group;
						/**
						 * if new group arrived, then need to push group unit ...
						 */
						if(Y_UNIT_IN_CHINESE[group * GROUP_NUMBER_IN_CHINESE].equals("" + number_C.charAt(number_C.length() - 1)) == false)
							number_C += Y_UNIT_IN_CHINESE[group * GROUP_NUMBER_IN_CHINESE];
					}
				}
				
				if(number_C != null && number_C.equals("") == false)
					sb.insert(0, number_C);
				
				index += 1;
				Y_part = Y_part.substring(0, Y_part.length() - 1);
			}
		}
		
		if(Y_UNIT_IN_CHINESE[0].equals("" + sb.charAt(sb.length() - 1)) == false)
			sb.append(Y_UNIT_IN_CHINESE[0]);
		
		if(F_part.equals("00")) {
			sb.append(Y_FINISH_IN_CHINESE);
		} else {
			int J;
			try {
				J = Integer.parseInt("" + F_part.charAt(0));
			} catch (NumberFormatException e) {
				return money_str;
			}
			
			int F;
			try {
				F = Integer.parseInt("" + F_part.charAt(1));
			} catch (NumberFormatException e) {
				return money_str;
			}

			sb.append("" + NUMBER_IN_CHINESE[J] + F_UNIT_IN_CHINESE[0]);
			sb.append("" + NUMBER_IN_CHINESE[F] + F_UNIT_IN_CHINESE[1]);
		}
		
		return sb.toString();
    }

	public static void main (final String[] args) {
        System.out.println(F2Y(0));
        System.out.println(F2Y(1));
        System.out.println(F2Y(3));
        System.out.println(F2Y(12));
        System.out.println(F2Y(78));
        System.out.println(F2Y(123));
        System.out.println(F2Y(627));
        System.out.println(F2Y(3627));
        System.out.println(F2Y(1233627));
        
        System.out.println(F2ChineseY(3L));
        System.out.println(F2ChineseY(30L));
        System.out.println(F2ChineseY(300L));
        System.out.println(F2ChineseY(3000L));
        System.out.println(F2ChineseY(30000L));
        System.out.println(F2ChineseY(300000L));
        System.out.println(F2ChineseY(3000000L));
        System.out.println(F2ChineseY(30000000L));
        System.out.println(F2ChineseY(300000000L));
        System.out.println(F2ChineseY(3000000000L));
        System.out.println(F2ChineseY(30000000000L));
        System.out.println(F2ChineseY(300000000000L));
        System.out.println(F2ChineseY(3000000000000L));
        System.out.println(F2ChineseY(30000000000000L));
        
        System.out.println(F2ChineseY(101L));
        System.out.println(F2ChineseY(10101L));
        System.out.println(F2ChineseY(100101L));
        System.out.println(F2ChineseY(1000101L));
        System.out.println(F2ChineseY(10100101L));
        System.out.println(F2ChineseY(100000101L));
        System.out.println(F2ChineseY(1000000101L));
        System.out.println(F2ChineseY(10000000101L));
        System.out.println(F2ChineseY(100000000101L));
        System.out.println(F2ChineseY(1000000000101L));
        System.out.println(F2ChineseY(10000000000101L));
        System.out.println(F2ChineseY(100000000000101L));
        System.out.println(F2ChineseY(100000000010101L));
        System.out.println(F2ChineseY(100000000100101L));
        System.out.println(F2ChineseY(100000001000101L));
        System.out.println(F2ChineseY(100000011000101L));
        System.out.println(F2ChineseY(100000101000101L));
        System.out.println(F2ChineseY(100001001000101L));
        System.out.println(F2ChineseY(100011001000101L));
        System.out.println(F2ChineseY(100101001000101L));
        System.out.println(F2ChineseY(100111001000101L));
        System.out.println(F2ChineseY(101001001000101L));
        System.out.println(F2ChineseY(110001001000101L));
        System.out.println(F2ChineseY(111001001000101L));
        System.out.println(F2ChineseY(11010101010101L));
        System.out.println(F2ChineseY(11101101101101L));
        System.out.println(F2ChineseY(10110011001101L));
        System.out.println(F2ChineseY(10011001101101L));
        System.out.println(F2ChineseY(10001100110101L));
        System.out.println(F2ChineseY(10000110011101L));
        System.out.println(F2ChineseY(10000011001101L));
        System.out.println(F2ChineseY(10000001101101L));
        
        System.out.println(F2ChineseY(909L));
        System.out.println(F2ChineseY(90909L));
        System.out.println(F2ChineseY(900909L));
        System.out.println(F2ChineseY(9000909L));
        System.out.println(F2ChineseY(90900909L));
        System.out.println(F2ChineseY(900000909L));
        System.out.println(F2ChineseY(9000000909L));
        System.out.println(F2ChineseY(90000000909L));
        System.out.println(F2ChineseY(900000000909L));
        System.out.println(F2ChineseY(9000000000909L));
        System.out.println(F2ChineseY(90000000000909L));
        System.out.println(F2ChineseY(900000000000909L));
        System.out.println(F2ChineseY(900000000090909L));
        System.out.println(F2ChineseY(900000000900909L));
        System.out.println(F2ChineseY(900000009000909L));
        System.out.println(F2ChineseY(900000099000909L));
        System.out.println(F2ChineseY(900000909000909L));
        System.out.println(F2ChineseY(900009009000909L));
        System.out.println(F2ChineseY(900099009000909L));
        System.out.println(F2ChineseY(900909009000909L));
        System.out.println(F2ChineseY(900999009000909L));
        System.out.println(F2ChineseY(909009009000909L));
        System.out.println(F2ChineseY(990009009000909L));
        System.out.println(F2ChineseY(999009009000909L));
        System.out.println(F2ChineseY(99090909090909L));
        System.out.println(F2ChineseY(99909909909909L));
        System.out.println(F2ChineseY(90990099009909L));
        System.out.println(F2ChineseY(90099009909909L));
        System.out.println(F2ChineseY(90009900990909L));
        System.out.println(F2ChineseY(90000990099909L));
        System.out.println(F2ChineseY(90000099009909L));
        System.out.println(F2ChineseY(90000009909909L));
        
        System.out.println(F2ChineseY(0L));
        System.out.println(F2ChineseY(1L));
        System.out.println(F2ChineseY(2L));
        System.out.println(F2ChineseY(27L));
        System.out.println(F2ChineseY(38L));
        System.out.println(F2ChineseY(10L));
        System.out.println(F2ChineseY(11L));
        System.out.println(F2ChineseY(12L));
        System.out.println(F2ChineseY(50L));
        System.out.println(F2ChineseY(71L));
        System.out.println(F2ChineseY(82L));
        System.out.println(F2ChineseY(8200L));
        
        System.out.println(F2ChineseY(10L));
        System.out.println(F2ChineseY(11L));
        System.out.println(F2ChineseY(12L));
        System.out.println(F2ChineseY(127L));
        System.out.println(F2ChineseY(138L));
        System.out.println(F2ChineseY(110L));
        System.out.println(F2ChineseY(111L));
        System.out.println(F2ChineseY(112L));
        System.out.println(F2ChineseY(150L));
        System.out.println(F2ChineseY(171L));
        System.out.println(F2ChineseY(182L));
        System.out.println(F2ChineseY(18200L));

        System.out.println(F2ChineseY(70L));
        System.out.println(F2ChineseY(71L));
        System.out.println(F2ChineseY(72L));
        System.out.println(F2ChineseY(727L));
        System.out.println(F2ChineseY(738L));
        System.out.println(F2ChineseY(710L));
        System.out.println(F2ChineseY(711L));
        System.out.println(F2ChineseY(712L));
        System.out.println(F2ChineseY(750L));
        System.out.println(F2ChineseY(771L));
        System.out.println(F2ChineseY(782L));
        System.out.println(F2ChineseY(78200L));

        System.out.println(F2ChineseY(370L));
        System.out.println(F2ChineseY(371L));
        System.out.println(F2ChineseY(372L));
        System.out.println(F2ChineseY(3727L));
        System.out.println(F2ChineseY(3738L));
        System.out.println(F2ChineseY(3710L));
        System.out.println(F2ChineseY(3711L));
        System.out.println(F2ChineseY(3712L));
        System.out.println(F2ChineseY(3750L));
        System.out.println(F2ChineseY(3771L));
        System.out.println(F2ChineseY(3782L));
        System.out.println(F2ChineseY(378200L));

        System.out.println(F2ChineseY(5370L));
        System.out.println(F2ChineseY(5371L));
        System.out.println(F2ChineseY(5372L));
        System.out.println(F2ChineseY(53727L));
        System.out.println(F2ChineseY(53738L));
        System.out.println(F2ChineseY(53710L));
        System.out.println(F2ChineseY(53711L));
        System.out.println(F2ChineseY(53712L));
        System.out.println(F2ChineseY(53750L));
        System.out.println(F2ChineseY(53771L));
        System.out.println(F2ChineseY(53782L));
        System.out.println(F2ChineseY(5378200L));

        System.out.println(F2ChineseY(45370L));
        System.out.println(F2ChineseY(45371L));
        System.out.println(F2ChineseY(45372L));
        System.out.println(F2ChineseY(453727L));
        System.out.println(F2ChineseY(453738L));
        System.out.println(F2ChineseY(453710L));
        System.out.println(F2ChineseY(453711L));
        System.out.println(F2ChineseY(453712L));
        System.out.println(F2ChineseY(453750L));
        System.out.println(F2ChineseY(453771L));
        System.out.println(F2ChineseY(453782L));
        System.out.println(F2ChineseY(45378200L));

        System.out.println(F2ChineseY(645370L));
        System.out.println(F2ChineseY(645371L));
        System.out.println(F2ChineseY(645372L));
        System.out.println(F2ChineseY(6453727L));
        System.out.println(F2ChineseY(6453738L));
        System.out.println(F2ChineseY(6453710L));
        System.out.println(F2ChineseY(6453711L));
        System.out.println(F2ChineseY(6453712L));
        System.out.println(F2ChineseY(6453750L));
        System.out.println(F2ChineseY(6453771L));
        System.out.println(F2ChineseY(6453782L));
        System.out.println(F2ChineseY(645378200L));

        System.out.println(F2ChineseY(9645370L));
        System.out.println(F2ChineseY(9645371L));
        System.out.println(F2ChineseY(9645372L));
        System.out.println(F2ChineseY(96453727L));
        System.out.println(F2ChineseY(96453738L));
        System.out.println(F2ChineseY(96453710L));
        System.out.println(F2ChineseY(96453711L));
        System.out.println(F2ChineseY(96453712L));
        System.out.println(F2ChineseY(96453750L));
        System.out.println(F2ChineseY(96453771L));
        System.out.println(F2ChineseY(96453782L));
        System.out.println(F2ChineseY(9645378200L));

        System.out.println(F2ChineseY(19645370L));
        System.out.println(F2ChineseY(19645371L));
        System.out.println(F2ChineseY(19645372L));
        System.out.println(F2ChineseY(196453727L));
        System.out.println(F2ChineseY(196453738L));
        System.out.println(F2ChineseY(196453710L));
        System.out.println(F2ChineseY(196453711L));
        System.out.println(F2ChineseY(196453712L));
        System.out.println(F2ChineseY(196453750L));
        System.out.println(F2ChineseY(196453771L));
        System.out.println(F2ChineseY(196453782L));
        System.out.println(F2ChineseY(19645378200L));

        System.out.println(F2ChineseY(219645370L));
        System.out.println(F2ChineseY(219645371L));
        System.out.println(F2ChineseY(219645372L));
        System.out.println(F2ChineseY(2196453727L));
        System.out.println(F2ChineseY(2196453738L));
        System.out.println(F2ChineseY(2196453710L));
        System.out.println(F2ChineseY(2196453711L));
        System.out.println(F2ChineseY(2196453712L));
        System.out.println(F2ChineseY(2196453750L));
        System.out.println(F2ChineseY(2196453771L));
        System.out.println(F2ChineseY(2196453782L));
        System.out.println(F2ChineseY(219645378200L));

        System.out.println(F2ChineseY(8219645370L));
        System.out.println(F2ChineseY(8219645371L));
        System.out.println(F2ChineseY(8219645372L));
        System.out.println(F2ChineseY(82196453727L));
        System.out.println(F2ChineseY(82196453738L));
        System.out.println(F2ChineseY(82196453710L));
        System.out.println(F2ChineseY(82196453711L));
        System.out.println(F2ChineseY(82196453712L));
        System.out.println(F2ChineseY(82196453750L));
        System.out.println(F2ChineseY(82196453771L));
        System.out.println(F2ChineseY(82196453782L));
        System.out.println(F2ChineseY(8219645378200L));

        System.out.println(F2ChineseY(123141243242L));
        System.out.println(F2ChineseY(12314124324200L));
    }

}
