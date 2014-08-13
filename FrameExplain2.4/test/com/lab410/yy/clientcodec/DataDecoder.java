package com.lab410.yy.clientcodec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import java.nio.charset.Charset;
public class DataDecoder extends CumulativeProtocolDecoder {
	
	private Charset charset = null;
	public DataDecoder(Charset charset) {
		// TODO Auto-generated constructor stub
		this.charset = charset;
	}

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		if(in.limit() >= 5){
			String s = in.getString(Charset.forName("utf-8").newDecoder());
			out.write(s);
			return true;
		}
		return false;
	}

}
