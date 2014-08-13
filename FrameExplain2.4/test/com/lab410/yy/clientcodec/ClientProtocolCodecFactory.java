package com.lab410.yy.clientcodec;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
public class ClientProtocolCodecFactory implements ProtocolCodecFactory{

	public DataDecoder decoder = null;
	public DataEncoder encoder = null;
	public ClientProtocolCodecFactory(Charset charset) {
		// TODO Auto-generated constructor stub
		decoder = new DataDecoder(charset);
		encoder = new DataEncoder(charset);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

}
