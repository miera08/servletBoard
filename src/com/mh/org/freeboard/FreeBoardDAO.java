package com.mh.org.freeboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataSource;

import com.mg.org.util.DataSources;

import javafx.scene.chart.PieChart.Data;

public class FreeBoardDAO {
	public List<FreeBoardDTO> selectAll(int ipage,int lpage){
		List<FreeBoardDTO> list = new ArrayList<FreeBoardDTO>();

		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DataSources.getConnection();
//			pstmt = conn.prepareStatement("select top 7 * from freeboard order by reg_date desc");
			pstmt = conn.prepareStatement("select * from(select ROW_NUMBER() over (order by idx desc) rownum , * from freeboard ) a where rownum between ? and ?");

			pstmt.setInt(1, ipage);
			pstmt.setInt(2, lpage);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeBoardDTO dto = new FreeBoardDTO(
						rs.getInt("idx"),
						rs.getString("title") ,
						rs.getString("content"),
						rs.getString("reg_date"),
						rs.getString("mod_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSources.doClose(rs, pstmt, conn);
		}

		return list;
	}
	public void insertFreeboard(FreeBoardDTO dto) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = DataSources.getConnection();

		pstmt = conn.prepareStatement("exec PRO_SEQS 'free_board_seq'");
		pstmt.setEscapeProcessing(true); //프로시저실행시킬때 넣을것
		rs=pstmt.executeQuery();
		int idx=0;
		if(rs.next()) {
			idx = rs.getInt("value");
		}

		pstmt = conn.prepareStatement("insert into freeboard (idx,title,content,reg_date,mod_date) values (?,?,?,getdate(),getdate())");


		pstmt.setInt(1,idx);
		pstmt.setString(2,dto.getTitle());
		pstmt.setString(3,dto.getContent());

		pstmt.executeUpdate();

		DataSources.doClose(null, pstmt, conn);
	}
	public void deleteAll(String[] idx) throws Exception {
		String idxs="";
		for(int i = 0; i<idx.length;i++) {
			if((idx.length - 1)==i) { //마지막에 , 가 들어가면 안되게 하는것
				idxs += idx[i];
			}else {
				idxs += idx[i]+",";
			}
		}

		Connection conn=null;
		PreparedStatement pstmt = null;

		conn = DataSources.getConnection();
		pstmt = conn.prepareStatement("delete from freeboard where idx in ("+idxs+")");

		pstmt.executeUpdate();

		DataSources.doClose(null, pstmt, conn);
	}
	public FreeBoardDTO selectOne(String idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardDTO dto = null;
		try {
			conn = DataSources.getConnection();
			pstmt = conn.prepareStatement("select * from freeboard where idx = ?");
			pstmt.setInt(1, Integer.parseInt(idx));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new FreeBoardDTO(rs.getInt("idx"),
						rs.getString("title") ,
						rs.getString("content"),
						rs.getString("reg_date"),
						rs.getString("mod_date"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSources.doClose(null, pstmt, conn);
		}

		return dto;
	}

	public void updateFreeBoard(FreeBoardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DataSources.getConnection();
			pstmt = conn.prepareStatement("update freeboard set title=?, content=?, mod_date=getdate() where idx =?");

			pstmt.setString(1, dto.getTitle());
			pstmt.setString(1, dto.getContent());
			pstmt.setInt(1, dto.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSources.doClose(null, pstmt, conn);
		}


	}
	public int selectPageCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DataSources.getConnection();
			pstmt = conn.prepareStatement("select count(*) from freeboard");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int pagecount = rs.getInt(1);
				if(pagecount%7 > 0) {
					pagecount=(pagecount/7)+1;
				}else {
					pagecount=pagecount/7;
				}
				return pagecount;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSources.doClose(null, pstmt, conn);
		}


		return 0;
	}
}
