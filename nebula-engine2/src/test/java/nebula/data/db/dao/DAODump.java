package nebula.data.db.dao;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DAODump implements Opcodes {

	public byte[] dump() throws Exception {

		String clsDao = "nebula/data/db/dao/OrderDAO1";
		String clsDOHeader = "nebula/data/Order";
		String clsDODetail = "nebula/data/OrderDetail";

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, clsDao, "Ljava/lang/Object;Lnebula/data/db/dao/DAO<Lnebula/data/Order;>;", "java/lang/Object",
				new String[] { "nebula/data/db/dao/DAO" });

		cw.visitSource("OrderDAO.java", null);

		{
			fv = cw.visitField(0, "conn", "Ljava/sql/Connection;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(13, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "Lnebula/data/db/dao/OrderDAO;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		dumpQuery(clsDao, clsDOHeader, clsDODetail, cw);
		dumpGet(clsDao, clsDOHeader, clsDODetail, cw);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "setConn", "(Ljava/sql/Connection;)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(95, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitFieldInsn(PUTFIELD, clsDao, "conn", "Ljava/sql/Connection;");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(96, l1);
			mv.visitInsn(RETURN);
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLocalVariable("this", "Lnebula/data/db/dao/OrderDAO;", null, l0, l2, 0);
			mv.visitLocalVariable("conn", "Ljava/sql/Connection;", null, l0, l2, 1);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "get", "(I)Ljava/lang/Object;", null, new String[] { "java/sql/SQLException" });
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(1, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ILOAD, 1);
			mv.visitMethodInsn(INVOKEVIRTUAL, clsDao, "get", "(I)Lnebula/data/Order;");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}

	private void dumpQuery(String clsDao, String clsDOHeader, String clsDODetail, ClassWriter cw) {
		MethodVisitor mv;
		int tConn = 0;
		int tPstm = 1;
		int tResultSet = 2;
		int tDataList = 3;
		int tDOHeader = 4;
		int tId = 5;
		int tDOIterator = 6;
		int tDODetail = 7;

		{
			mv = cw.visitMethod(ACC_PUBLIC, "query", "()Ljava/util/List;", "()Ljava/util/List<Lnebula/data/Order;>;", new String[] { "java/sql/SQLException" });
			mv.visitCode();

			mv.visitTypeInsn(NEW, "java/util/ArrayList");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
			mv.visitVarInsn(ASTORE, tDataList);

			mv.visitVarInsn(ALOAD, tConn);
			mv.visitFieldInsn(GETFIELD, clsDao, "conn", "Ljava/sql/Connection;");
			mv.visitLdcInsn("select * from NOrder order by id");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/Connection", "prepareStatement", "(Ljava/lang/String;)Ljava/sql/PreparedStatement;");
			mv.visitVarInsn(ASTORE, tPstm);

			execQuery(mv, tPstm, tResultSet);

			Label checkHasRecord = new Label();
			mv.visitJumpInsn(GOTO, checkHasRecord);

			Label whenHasRecord = new Label();
			mv.visitLabel(whenHasRecord);
			{
				makeDOHeader(clsDOHeader, mv, tDOHeader);

				fillDOHeader(clsDOHeader, mv, tResultSet, tDOHeader);

				addDataToList(mv, tDataList, tDOHeader);
			}
			mv.visitLabel(checkHasRecord);
			mv.visitVarInsn(ALOAD, tResultSet);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
			mv.visitJumpInsn(IFNE, whenHasRecord);

			mv.visitVarInsn(ALOAD, tConn);
			mv.visitFieldInsn(GETFIELD, clsDao, "conn", "Ljava/sql/Connection;");
			mv.visitLdcInsn("select * from NOrder_Detail order by order_id,seq");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/Connection", "prepareStatement", "(Ljava/lang/String;)Ljava/sql/PreparedStatement;");
			mv.visitVarInsn(ASTORE, tPstm);

			execQuery(mv, tPstm, tResultSet);

			mv.visitVarInsn(ALOAD, tResultSet);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
			Label whenHasnotRecord = new Label();
			mv.visitJumpInsn(IFEQ, whenHasnotRecord);
			{
				getKeyToVar(mv, tResultSet, tId);

				mv.visitVarInsn(ALOAD, tDataList);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;");
				mv.visitVarInsn(ASTORE, tDOIterator);

				Label checkHasMoreDetailRecord = new Label();
				mv.visitJumpInsn(GOTO, checkHasMoreDetailRecord);
				Label whenHasMoreDetailRecord = new Label();
				mv.visitLabel(whenHasMoreDetailRecord);

				{
					mv.visitVarInsn(ALOAD, tDOIterator);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;");
					mv.visitTypeInsn(CHECKCAST, clsDOHeader);
					mv.visitVarInsn(ASTORE, tDOHeader);

					Label checkHasMoreDO = new Label();
					mv.visitJumpInsn(GOTO, checkHasMoreDO);

					Label whenHasMoreDO = new Label();
					mv.visitLabel(whenHasMoreDO);

					makeDOHeader(clsDODetail, mv, tDODetail);

					fillDODetail(clsDODetail, mv, tResultSet, tDODetail);

					addDetailToHeader(clsDOHeader, mv, tDOHeader, tDODetail);

					mv.visitVarInsn(ALOAD, tResultSet);
					mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
					Label whenHasMoreRecord = new Label();
					mv.visitJumpInsn(IFNE, whenHasMoreRecord);
					mv.visitJumpInsn(GOTO, whenHasnotRecord);
					mv.visitLabel(whenHasMoreRecord);

					getKeyToVar(mv, tResultSet, tId);

					mv.visitLabel(checkHasMoreDO);

					mv.visitVarInsn(ILOAD, tId);
					mv.visitVarInsn(ALOAD, tDOHeader);
					mv.visitMethodInsn(INVOKEVIRTUAL, clsDOHeader, "getId", "()I");
					mv.visitJumpInsn(IF_ICMPEQ, whenHasMoreDO);

				}
				mv.visitLabel(checkHasMoreDetailRecord);
				mv.visitVarInsn(ALOAD, tDOIterator);
				mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z");
				mv.visitJumpInsn(IFNE, whenHasMoreDetailRecord);

			}
			mv.visitLabel(whenHasnotRecord);

			mv.visitVarInsn(ALOAD, tDataList);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(tDataList, 8);
			mv.visitEnd();
		}
	}

	private void addDataToList(MethodVisitor mv, int tDataList, int tDOHeader) {
		mv.visitVarInsn(ALOAD, tDataList);
		mv.visitVarInsn(ALOAD, tDOHeader);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);
	}

	private void getKeyToVar(MethodVisitor mv, int tResultSet, int tId) {
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_1);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitVarInsn(ISTORE, tId);
	}

	private void addDetailToHeader(String clsDOHeader, MethodVisitor mv, int tDOHeader, int tDODetail) {
		mv.visitVarInsn(ALOAD, tDOHeader);
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDOHeader, "getDetails", "()Ljava/util/List;");
		mv.visitVarInsn(ALOAD, tDODetail);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z");
		mv.visitInsn(POP);
	}

	private void makeDOHeader(String clsDOHeader, MethodVisitor mv, int tDOHeader) {
		mv.visitTypeInsn(NEW, clsDOHeader);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, clsDOHeader, "<init>", "()V");
		mv.visitVarInsn(ASTORE, tDOHeader);
	}

	private void fillDOHeader(String clsDOHeader, MethodVisitor mv, int tResultSet, int tDOHeader) {
		mv.visitVarInsn(ALOAD, tDOHeader);
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_1);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDOHeader, "setId", "(I)V");
	}

	private void dumpGet(String clsDao, String clsDOHeader, String clsDODetail, ClassWriter cw) {
		MethodVisitor mv;
		int tThis = 0;
		int pID = 1;
		int tPstm = 2;
		int tResultSet = 3;
		int tDOHeader = 4;
		int tDODetail = 5;

//
//		0mv.visitLocalVariable("this", "Lnebula/data/db/dao/OrderDAO;", null, l0, l23, 0);
//		1mv.visitLocalVariable("id", "I", null, l0, l23, 1);
//		2mv.visitLocalVariable("psmt", "Ljava/sql/PreparedStatement;", null, l2, l23, 2);
//		3mv.visitLocalVariable("res", "Ljava/sql/ResultSet;", null, l4, l23, 3);
//		4mv.visitLocalVariable("o", "Lnebula/data/Order;", null, l1, l23, 4);
//		5mv.visitLocalVariable("od", "Lnebula/data/OrderDetail;", null, l16, l14, 5);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "get", "(I)Lnebula/data/Order;", null, new String[] { "java/sql/SQLException" });
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(61, l0);
			makeDOHeader(clsDOHeader, mv, tDOHeader);

			mv.visitVarInsn(ALOAD, tThis);
			mv.visitFieldInsn(GETFIELD, clsDao, "conn", "Ljava/sql/Connection;");
			mv.visitLdcInsn("select * from NOrder  where id=? order by id");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/Connection", "prepareStatement", "(Ljava/lang/String;)Ljava/sql/PreparedStatement;");
			mv.visitVarInsn(ASTORE, tPstm);

			setGetKeyParameter(mv, pID, tPstm);

			execQuery(mv, tPstm, tResultSet);

			mv.visitVarInsn(ALOAD, tResultSet);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
			Label l5 = new Label();
			mv.visitJumpInsn(IFEQ, l5);

			fillDOHeader(clsDOHeader, mv, tResultSet, tDOHeader);

			mv.visitLabel(l5);
			mv.visitLineNumber(73, l5);
			mv.visitFrame(Opcodes.F_APPEND, tResultSet, new Object[] { "java/sql/PreparedStatement", "java/sql/ResultSet", clsDOHeader }, tThis, null);
			mv.visitVarInsn(ALOAD, tThis);
			mv.visitFieldInsn(GETFIELD, clsDao, "conn", "Ljava/sql/Connection;");
			mv.visitLdcInsn("select * from NOrder_Detail  where order_id=? order by order_id,seq");
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/Connection", "prepareStatement", "(Ljava/lang/String;)Ljava/sql/PreparedStatement;");
			mv.visitVarInsn(ASTORE, tPstm);

			setGetKeyParameter(mv, pID, tPstm);

			execQuery(mv, tPstm, tResultSet);

			mv.visitVarInsn(ALOAD, tResultSet);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
			Label l12 = new Label();
			mv.visitJumpInsn(IFEQ, l12);

			Label l14 = new Label();
			mv.visitJumpInsn(GOTO, l14);
			Label l15 = new Label();
			mv.visitLabel(l15);
			mv.visitLineNumber(79, l15);
			mv.visitFrame(Opcodes.F_SAME, tThis, null, tThis, null);
			makeDOHeader(clsDODetail, mv, tDODetail);

			fillDODetail(clsDODetail, mv, tResultSet, tDODetail);

			addDetailToHeader(clsDOHeader, mv, tDOHeader, tDODetail);

			mv.visitLabel(l14);
			mv.visitLineNumber(78, l14);
			mv.visitFrame(Opcodes.F_SAME, tThis, null, tThis, null);
			mv.visitVarInsn(ALOAD, tResultSet);
			mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "next", "()Z");
			mv.visitJumpInsn(IFNE, l15);
			mv.visitLabel(l12);
			mv.visitLineNumber(90, l12);
			mv.visitFrame(Opcodes.F_SAME, tThis, null, tThis, null);
			mv.visitVarInsn(ALOAD, tDOHeader);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(tResultSet, 6);
			mv.visitEnd();
		}
	}

	private void execQuery(MethodVisitor mv, int tPstm, int tResultSet) {
		mv.visitVarInsn(ALOAD, tPstm);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/PreparedStatement", "executeQuery", "()Ljava/sql/ResultSet;");
		mv.visitVarInsn(ASTORE, tResultSet);
	}

	private void setGetKeyParameter(MethodVisitor mv, int pID, int tPstm) {
		mv.visitVarInsn(ALOAD, tPstm);
		mv.visitInsn(ICONST_1);
		mv.visitVarInsn(ILOAD, pID);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/PreparedStatement", "setInt", "(II)V");
	}

	private void fillDODetail(String clsDODetail, MethodVisitor mv, int tResultSet, int tDODetail) {
		mv.visitVarInsn(ALOAD, tDODetail);
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_2);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDODetail, "setSeq", "(I)V");
		
		mv.visitVarInsn(ALOAD, tDODetail);
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_3);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDODetail, "setPrice", "(I)V");
		
		mv.visitVarInsn(ALOAD, tDODetail);
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_4);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDODetail, "setCount", "(I)V");
		
		mv.visitVarInsn(ALOAD, tDODetail);
		mv.visitVarInsn(ALOAD, tResultSet);
		mv.visitInsn(ICONST_5);
		mv.visitMethodInsn(INVOKEINTERFACE, "java/sql/ResultSet", "getInt", "(I)I");
		mv.visitMethodInsn(INVOKEVIRTUAL, clsDODetail, "setAmount", "(I)V");
	}
}
