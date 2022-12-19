package com.auto.message;

import java.io.ByteArrayInputStream;
//import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
//import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;


public class ckhDnaServ {
	
	public static final int	SYS_VER_VIDEO		= 1;	// 영상 비디오 DNA 검색
	public static final int	SYS_VER_MUSIC		= 2;	// 음악 DNA 검색
	public static final int	SYS_VER_AUDIO		= 3;	// 영상 음원 DNA 검색
	public static final int	SYS_VER_ETRI_VIDEO	= 4;	// ETRI 비디오 오디오 DNA 검색
	
	public static final int	SEARCH_DEFAULT_TIMEOUT	= 30;		// 검색 디폴트 타임아웃 30초
	public static final int	SEARCH_DEFAULT_TIMEOUT_P2P	= 5;	// 검색 디폴트 타임아웃 P2P는 5초
	//public static final int		SEARCH_DEFAULT_TIMEOUT_P2P	= 10;	// 검색 디폴트 타임아웃 P2P는 5초
	
	public static final int			m_sys_ver = SYS_VER_AUDIO;				// sys env 버전
	public static final int			m_search_server_gubun = 0;	// 0 : 전체 검색(Fast, 일반) , 1 : Fast만 검색, 2 : 일반만 검색
	
	

// 전문 :
//
//		검색서버로 보낼때
//			4	: 보낼패킷사이즈 ; 전체 사이즈 - 4
//			3	: 응답코드 (보낼때는 안씀 : SPACE(0x20)로)
//			3	: client -> server로 보냇 소켓 횟수 (여기에서는 안씀)
//			20	: 클라이언트 아이피 (로그용)
//			xxx	: DNA 데이터
//			
	public class DNASearchSendFormat {
		
		// header
		String	sendLen;			// 4 : sendLen를 뺀 패킷 사이즈
		String	retCode;			// 3 : 응답코드 (보낼때는 클라이언트 디바이스 명
		String	sendSocketCount;	// 3 : client -> server로 보냇 소켓 횟수 ("001" 로 세팅)
		String	sendClientIP;		// 20 : 클라이언트 아이피 (로그용)
		
		// header
		byte[]	dnaData;
		int m_sys_ver=0;
		
		public DNASearchSendFormat(int sys_ver){
			m_sys_ver = sys_ver;
		}
		
		public void setDNAData(int sys_ver, int dna_count, String[] dna_index_array, String[] fdna_array, String remote_addr, int dnagen_start_sec) {
			
			int aaa= dna_count * 6;
			ByteBuffer buf = ByteBuffer.allocate(aaa);
					
			buf.clear(); // ByteBuffer �ʱ�ȭ   

			buf.order(ByteOrder.LITTLE_ENDIAN);	// window로 보내기 위해서 Little endian으로 변환
			
			for(int i=0; i<dna_count; i++) {
				
				String dna_index_array1 = dna_index_array[i];
				String fdna_array1 = fdna_array[i];
				
				short dna_index_array2 = 0;
				int fdna_array2 = 0;
				//long fdna_array2 = 0;
				try{
					dna_index_array2 = Short.parseShort(dna_index_array1);
					fdna_array2 = Integer.parseInt(fdna_array1);	
					//fdna_array2 = Long.parseLong(fdna_array1);
				}catch(Exception e){
					fdna_array2 = Integer.parseInt(fdna_array[i-1]);
				}   
				
				buf.putShort(dna_index_array2);
				buf.putInt(fdna_array2);
			}
			
			dnaData = buf.array();

			sendLen = String.valueOf(3 + 3 + 20 + dnaData.length);
			for(int i=0; i<(4 - sendLen.length()); i++) {
				sendLen = "0" + sendLen;	
			}
			
			
			retCode = "AUD";
			sendSocketCount = "001";
			
			sendClientIP  = String.valueOf(dnagen_start_sec);
			sendClientIP +=	"^";
			sendClientIP += remote_addr;
			
			for(int j=sendClientIP.length(); j<20; j++) {	// 20 자리 채우기
				sendClientIP = sendClientIP + " ";	
			}		
		}	

		public byte[] getByteFormat() {
			
			StringBuffer strBuf = new StringBuffer();
			ByteArrayOutputStream output = new ByteArrayOutputStream(4+3+3+20+dnaData.length);
			strBuf.append(sendLen);
			strBuf.append(retCode);
			strBuf.append(sendSocketCount);
			strBuf.append(sendClientIP);
			
			byte[] header = strBuf.toString().getBytes();

			output.write(header, 0, header.length);
			output.write(dnaData, 0, dnaData.length);
			
			byte[] byteFormat = output.toByteArray();
			
			try {
				output.close();	
			} catch (IOException ie) {
				ie.printStackTrace(System.out);
				//DNASearchLog.writeErr(ie.getMessage(), true,m_sys_ver);
			}
			
			return byteFormat;
		}
		
	}	
	
	
// 전문 :
//
//		검색서버에서 받을 때
//			4	: 보낼패킷사이즈 ; 전체 사이즈 - 4
//			3	: 응답코드
//			3	: 검색된 곡 수 (검색에 성공하면 1 이 옴)
//			
	public class DNASearchRecvFormat {
		
		// header
		String	recvLen;			// 4 : recvLen를 뺀 패킷 사이즈
		String	retCode;			// 3 : 응답코드 (보낼때는 안씀 : SPACE(0x20)로)
		String	retMusicCount;		// 3 : 검색된 곡 수 (검색에 성공하면 1 이 옴)
		String 	retClientIP;		// 20 : 받을때는 의미 없음
		// header                   
		String	music_id;			// 10 : music_id
		String	music_title;		// 250 : music_title
		String 	artist_nm;			// 60 : artist_nm
		String 	album_nm;			// 50 : album_nm
		String 	frame_seq;			// 5 : frame_seq
		int m_sys_ver =1;
		
		public DNASearchRecvFormat(int sys_ver){
			m_sys_ver = sys_ver;
		}
		
		// recvLen 을 뺀 byte
		public void setFormat(byte[] format) {
			
			byte[] b;
			ByteArrayInputStream input = new ByteArrayInputStream(format);	
			
			try {
				
				b = new byte[3];
				input.read(b, 0, 3);
				retCode = new String(b);
				
				b = new byte[3];
				input.read(b, 0, 3);
				retMusicCount = new String(b);
				
				if(Integer.parseInt(recvLen) <= 10) {
					return;	
				}
				
				b = new byte[20];
				input.read(b, 0, 20);
				retClientIP = new String(b);
				
				b = new byte[10];
				input.read(b, 0, 10);
				music_id = new String(b);
				music_id = music_id.trim();
				
				b = new byte[250];
				input.read(b, 0, 250);
				music_title = new String(b);
				music_title = music_title .trim();			
				
				b = new byte[60];
				input.read(b, 0, 60);
				artist_nm = new String(b);
				artist_nm = artist_nm.trim();
				
				b = new byte[50];
				input.read(b, 0, 50);
				album_nm = new String(b);
				album_nm = album_nm.trim();
				
				b = new byte[5];
				input.read(b, 0, 5);
				frame_seq = new String(b);
				
			} catch (Exception e) {
				e.printStackTrace(System.out);
				//DNASearchLog.writeErr(e.getMessage(), true, m_sys_ver);
			}
			
			try {
				input.close();	
			} catch (IOException ie) {
				ie.printStackTrace(System.out);
				//DNASearchLog.writeErr(ie.getMessage(), true, m_sys_ver);
			}
		}

		
	}	
	

	public class DNASearch {
		
		private String 	m_ip;
		private String 	m_port;
		private byte[]	m_send_format;
		private int		m_timeout_mill;
		
		public DNASearch(String ip, String port, byte[] send_format, int timeout_mill) {
			m_ip 			= ip;
			m_port			= port;
			m_send_format 	= send_format;
			m_timeout_mill 	= timeout_mill;
		} 
		
		public void run() {
			Socket 			sock = null;
			OutputStream 	out = null;
			InputStream		in = null;
			
			try {	
			
				sock 	= new Socket();
				sock.connect(new InetSocketAddress(m_ip, Integer.parseInt(m_port)),m_timeout_mill);	// 소켓을 connect 하되 타임아웃은 5초로
				
				//sock = new Socket(m_ip, Integer.parseInt(m_port));
				sock.setSoTimeout(m_timeout_mill);	// 소켓이 연결된 후에 5초동안 패킷이동이 없을 경우 연결을 끊는다.(타임아웃 밀리 세컨드)

				out 	= sock.getOutputStream();
				in 		= sock.getInputStream();
				
				out.write(m_send_format);
				out.flush();
				
				String 	strRecvLen;
				int		nRecvLen;
				byte[] 	byteRecvLen = new byte[4];
				byte[]	recvData;
				
				in.read(byteRecvLen);
				
				strRecvLen = new String(byteRecvLen);
				nRecvLen = Integer.parseInt(strRecvLen);
				recvData = new byte[nRecvLen];
				
				in.read(recvData);
				
				DNASearchRecvFormat recvFormat = new DNASearchRecvFormat(m_sys_ver);
				recvFormat.recvLen = strRecvLen;
				recvFormat.setFormat(recvData);	
				
				
				// 검색에 성공했음!!
				if(recvFormat.retCode.equals("000") && recvFormat.music_id != null && !recvFormat.music_id.equals("")) {
					//endThread(m_ip, m_port, this, true, recvFormat.retCode, recvFormat.music_id, recvFormat.music_title, recvFormat.artist_nm, recvFormat.album_nm);				
					System.out.println(m_ip +"\t성공\t" + recvFormat.music_id);
				} else {
					//endThread(m_ip, m_port, this, false, recvFormat.retCode, null, null, null, null);
					System.out.println(m_ip +"\t--------[실패]");
				}
			} catch(SocketTimeoutException ste) {
				ste.printStackTrace(System.out);
				
				Date currTime = new Date();
				String strCurrTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(currTime);	
				
				System.out.println(strCurrTime + " : " + "[SocketTimeoutException] " + "IP:" + m_ip + ", PORT:" + Integer.parseInt(m_port));	
				
			} catch(ConnectException ce) {
				ce.printStackTrace(System.out);	

				Date currTime = new Date();
				String strCurrTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(currTime);	
				
				System.out.println(strCurrTime + " : " + "[ConnectException] IP:" + m_ip + ", PORT:" + Integer.parseInt(m_port));
				
			} catch(UnknownHostException ue) {
				ue.printStackTrace(System.out);

				Date currTime = new Date();
				String strCurrTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(currTime);	
				
				System.out.println(strCurrTime + " : " + "[UnknownHostException] IP:" + m_ip + ", PORT:" + Integer.parseInt(m_port));
			} catch(IOException ioe) {
				ioe.printStackTrace(System.out);

				Date currTime = new Date();
				String strCurrTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(currTime);	
	
				System.out.println(strCurrTime + " : " + "[IOException] IP:" + m_ip + ", PORT:" + Integer.parseInt(m_port));
			} catch(Exception e) {
				e.printStackTrace(System.out);
		
				Date currTime = new Date();
				String strCurrTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(currTime);	

				System.out.println(strCurrTime + " : " + "[Exception] IP:" + m_ip + ", PORT:" + Integer.parseInt(m_port));	
			} finally {
				// 2006.12.04
				// 검색서버 TIMIE_WAIT 없애기 위해서
				try { sock.setSoLinger(true, 0);} catch(Exception e) {}
				try { out.close(); } catch (Exception e) {}
				try { in.close(); } catch (Exception e) {}
				try { sock.close(); } catch (Exception e) {}	
			}		
		} // end of run()		
	}
	
	public  void testDnaServ(String ip,String port) {
		
		int nTimeout = SEARCH_DEFAULT_TIMEOUT_P2P;
		int timeout_mill =  nTimeout * 1000;
		//int max_timeout_mill  	= System.currentTimeMillis() + timeout_mill;
		
		DNASearchSendFormat sendFormat = new DNASearchSendFormat(m_sys_ver);
		
		int m_dna_count = 16;
		String m_dna_index_array[] = {"0","16","32","48","64","80","96","112","128","144","160","176","192","208","224","240"};
		String m_fdna_array[] = {"455921457","-33546689","149101793","-1005000802","-470285553","503831619","-2144219143",
								 "-133423345","1021567216","-1838757582","-955004465","2029919856","234750015","-67043360",
								 "41916548","-477134457"};
		String m_remote_addr="127.0.0.1";
		int m_dnagen_start_sec = -1;
		sendFormat.setDNAData(m_sys_ver, m_dna_count, m_dna_index_array, m_fdna_array, m_remote_addr, m_dnagen_start_sec);	
		byte[] byteSendFormat = sendFormat.getByteFormat();
		
		DNASearch dnaServ = new DNASearch(ip, port, byteSendFormat, timeout_mill);
		dnaServ.run();
	}
	
	public  void test(String ip,String port) {
		System.out.println("Hello test World!");
		try {
			Thread.sleep(1000);
		} catch(Exception ex1) {
			
		}
	}
	
	public static void main(String[] args) {
		String[] get = {"121.254.128.171","121.254.128.172","121.254.128.173","121.254.128.174","121.254.128.175"
					   ,"121.254.128.176","121.254.128.177","121.254.128.178","121.254.128.179","121.254.128.180"
					   ,"121.254.128.181","121.254.128.182","121.254.128.183","121.254.128.184","121.254.128.185"
					   ,"121.254.128.186","121.254.128.187","121.254.128.188","121.254.128.189","121.254.128.190"
					   ,"121.254.128.191","121.254.128.192","121.254.128.193","121.254.128.194","121.254.128.195"
					   ,"121.254.128.196","121.254.128.197","121.254.128.198","121.254.128.199","121.254.128.200"
					   ,"121.254.128.201","121.254.128.202","121.254.128.203","121.254.128.204","121.254.128.205"
					   ,"121.254.128.206","121.254.128.207","121.254.128.208","121.254.128.209","121.254.128.210"
					   ,"121.254.128.211","121.254.128.212"};
		
		
		String ip=""; //성능평가 영상소리
		String port="30000";
		ckhDnaServ serv = new ckhDnaServ();
		
		for (String string : get) {
			LocalTime start = LocalTime.now();
			ip = string;
			serv.testDnaServ(ip, port);
			LocalTime end = LocalTime.now();
			Duration duration = Duration.between(start, end);
			double d_time = (double)duration.getSeconds() + (double)duration.getNano()/1000000000.0;
			System.out.println(ip + "\t" +  d_time + "\t");
		}
		//serv.test(ip, port);
	}
}

